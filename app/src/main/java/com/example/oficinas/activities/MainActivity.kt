package com.example.oficinas.activities


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import com.example.oficinas.utils.*
import kotlinx.android.synthetic.main.activity_login.linearLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class MainActivity : AppCompatActivity() {


	private lateinit var binding: ActivityMainBinding
//	private val model: CheckViewModel by viewModels()

	@SuppressLint("SetTextI18n")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
//		setSupportActionBar(toolbar)
		setContentView(binding.root)
//
//		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//		val linearLayoutManager = LinearLayoutManager(applicationContext)
//		linearLayoutManager.orientation = RecyclerView.VERTICAL
//
//		val adapter = CheckViewAdapter(applicationContext)
//		checkListRv.layoutManager = linearLayoutManager
//		checkListRv.adapter = adapter

//		model.list.observe(this as LifecycleOwner) {
//			if (it.isNotEmpty()) {
//				adapter.addAll(it)
//			}
//		}


//		BntGetChecklist.setOnClickListener {
//			val id: String = idCheckListEt.text.toString().trim()
//			if (id.isNotEmpty()) {
//				getCheckList(id)
//			} else {
//				BntGetChecklist.requestFocus()
//				Toast.makeText(applicationContext, "Digite o Id", Toast.LENGTH_LONG).show()
//				return@setOnClickListener
//			}
//		}


	}

	@Deprecated("Deprecated in Java", ReplaceWith("this.moveTaskToBack(true)"))
	override fun onBackPressed() {
		this.moveTaskToBack(true)
	}



}





