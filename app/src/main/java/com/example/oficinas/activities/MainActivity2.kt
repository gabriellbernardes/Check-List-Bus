package com.example.oficinas.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oficinas.activities.check.CheckViewAdapter
import com.example.oficinas.activities.check.CheckViewModel
import com.example.oficinas.databinding.ActivityMainBinding
import com.example.oficinas.network.api.RetrofitClient
import com.example.oficinas.network.models.data.Check
import com.example.oficinas.network.models.data.CheckListViewModel
import com.example.oficinas.network.response.ApiResponse
import com.example.oficinas.storage.SessionManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.BntGetChecklist
import kotlinx.android.synthetic.main.activity_main.sendCheckListBnt
import kotlinx.android.synthetic.main.activity_main.checkListRv
import kotlinx.android.synthetic.main.activity_main.idCheckListEt
import kotlinx.android.synthetic.main.activity_main.logoutButton
import kotlinx.android.synthetic.main.activity_main.materialCardView
import kotlinx.android.synthetic.main.activity_main.reloadBt
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : ComponentActivity() {
	private lateinit var binding: ActivityMainBinding
	private val model: CheckViewModel by viewModels()


	companion object {
		val hashMapList = mutableListOf<LinkedHashMap<String, Any?>>()

		var idResponse: String = ""
		fun addListResponse(hashMap: LinkedHashMap<String, Any?>) {
			if (hashMapList.contains(hashMap)) {
				println("O HashMap já existe na lista. Não será adicionado.")
			} else {
				hashMapList.add(hashMap)
				Log.d("---------------", hashMapList.toString())
				println("HashMap adicionado à lista.")
			}
		}
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


		val linearLayoutManager = LinearLayoutManager(applicationContext)
		linearLayoutManager.orientation = RecyclerView.VERTICAL

		val adapter = CheckViewAdapter(applicationContext)
		checkListRv.layoutManager = linearLayoutManager
		checkListRv.adapter = adapter

		model.list.observe(this as LifecycleOwner) {
			if (it.isNotEmpty()) {
				materialCardView.visibility = View.GONE
				reloadBt.visibility = View.VISIBLE
				sendCheckListBnt.visibility = View.VISIBLE
				adapter.addAll(it)
			}
		}


		reloadBt.setOnClickListener {
			materialCardView.visibility = View.VISIBLE
			reloadBt.visibility = View.GONE

		}


		BntGetChecklist.setOnClickListener {
			val id: String = idCheckListEt.text.toString().trim()
			if (id.isNotEmpty()) {
				getCheckList(id)
			} else {
				BntGetChecklist.requestFocus()
				Toast.makeText(applicationContext, "Digite o Id", Toast.LENGTH_LONG).show()
				return@setOnClickListener
			}
		}

		logoutButton.setOnClickListener {
			SessionManager(applicationContext).removeToken()
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
			finish()
		}

		sendCheckListBnt.setOnClickListener {
			if (hashMapList.size > 0) {
				hashMapList.forEach {
					val jsonString = Gson().toJson(it)
					val res = Gson().fromJson(jsonString, ApiResponse::class.java)
					sendCheckList(res)
				}
			} else {
				Toast.makeText(
					applicationContext,
					"Preencha o CheckList !!",
					Toast.LENGTH_LONG
				).show()
			}
		}

	}

	@Deprecated("Deprecated in Java", ReplaceWith("this.moveTaskToBack(true)"))
	override fun onBackPressed() {
		this.moveTaskToBack(true)
	}

	private fun getCheckList(id: String) {
		val preferences = this.getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
		val bearer = preferences.getString("token", "")
		RetrofitClient.instance.getCheckListAutorized(bearer!!, "shortlist")
			.enqueue(
				object : Callback<CheckListViewModel> {
					override fun onResponse(
						call: Call<CheckListViewModel>, response: Response<CheckListViewModel>
					) {
						Log.d("result --", response.toString())
						if (response.code() == 200) {
							idResponse = id
							val inputMethodManager =
								getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
							inputMethodManager.hideSoftInputFromWindow(idCheckListEt.windowToken, 0)

							val list: ArrayList<Check>? = response.body()?.checks

							list.apply {
								list?.forEach { check -> Log.d("-teste-", check.toString()) }
							}
							model.list.value = list

						} else if (response.code() == 404) {
							val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
							Toast.makeText(
								applicationContext,
								jsonObj.getString("message"),
								Toast.LENGTH_LONG
							).show()
						}

					}

					override fun onFailure(call: Call<CheckListViewModel>, t: Throwable) {
						Toast.makeText(
							applicationContext,
							"Falha na conexão!",
							Toast.LENGTH_LONG
						).show()

					}

				}
			)
	}


	private fun sendCheckList(check: ApiResponse) {
		val id = idResponse
		val preferences = this.getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
		val bearer = preferences.getString("token", "")
		RetrofitClient.instance.sendResponse(
			bearer!!,
			id,
			check.id,
			check.response,
			check.responseOptions,
			check.text,
			check.commentText
		)
			.enqueue(
				object : Callback<ResponseBody> {
					override fun onResponse(
						call: Call<ResponseBody>, response: Response<ResponseBody>
					) {
						Log.d("result --", response.toString())
						if (response.code() == 200) {
							Toast.makeText(
								applicationContext,
								"Enviado",
								Toast.LENGTH_LONG
							).show()

						} else {
							Toast.makeText(
								applicationContext,
								"error!!",
								Toast.LENGTH_LONG
							).show()
						}
					}


					override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
						Toast.makeText(
							applicationContext,
							"Falha na conexão!",
							Toast.LENGTH_LONG
						).show()

					}

				}
			)
	}

}


