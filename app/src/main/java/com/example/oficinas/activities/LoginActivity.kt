package com.example.oficinas.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.oficinas.R
import com.example.oficinas.network.api.RetrofitClient
import com.example.oficinas.network.models.login.LoginRequest
import com.example.oficinas.network.response.LoginResponse
import com.example.oficinas.storage.SessionManager
import kotlinx.android.synthetic.main.activity_login.loginButton
import kotlinx.android.synthetic.main.activity_login.passwordText
import kotlinx.android.synthetic.main.activity_login.usernameText
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        loginButton.setOnClickListener {
            val authRes = LoginRequest()
//            usernameText.setText("1111")
//            passwordText.setText("1965")
            authRes.username = usernameText.text.toString().trim()
            authRes.password = passwordText.text.toString().trim()


            if ( authRes.username.isEmpty()) {
                usernameText.error = "Username requerido"
                usernameText.requestFocus()
                return@setOnClickListener

            }

            if ( authRes.password.isEmpty()) {
                usernameText.error = "Password requerido"
                usernameText.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.login(authRes)
                .enqueue(
                    object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>, response: Response<LoginResponse>
                        ) {
                            Log.d("result --", response.toString())
                            val loginResponse = response.body()
                            Log.d("result --", loginResponse.toString())

                            Log.d("result --", response.errorBody().toString())

                            if (response.code() == 200) {

                                Toast.makeText(
                                    applicationContext,
                                    "Deu Bom!!!!",
                                    Toast.LENGTH_LONG
                                ).show()
                                SessionManager(applicationContext).saveLogin(loginResponse!!)

                                val intent = Intent(
                                    applicationContext,
                                    MainActivity2::class.java
                                )
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)

                            } else if (response.code() == 404) {
                                val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                                Toast.makeText(
                                    applicationContext,
                                    jsonObj.getString("message"),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Falha na conexão!",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    })
        }
    }



}

