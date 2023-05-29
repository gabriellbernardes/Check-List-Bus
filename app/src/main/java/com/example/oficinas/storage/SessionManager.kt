package com.example.oficinas.storage

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.oficinas.network.response.LoginResponse


class SessionManager(private val mCtx: Context) : AppCompatActivity() {

    companion object {

        const val SHARED_PREF_NAME = "my_shared_preff"
        var id = ""


        @SuppressLint("StaticFieldLeak")
        private var mInstance: SessionManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SessionManager {
            if (mInstance == null) {
                mInstance = SessionManager(mCtx)
            }
            return mInstance as SessionManager
        }

    }

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("logged", -1) != -1
        }

    fun saveLogin(user: LoginResponse) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", "Bearer " + user.token.accessToken)
        editor.putString("nameUser", user.user.name)
        editor.putLong("idUser", user.user.id)
        editor.putInt("logged", 1)
        editor.apply()
    }

    fun removeToken(){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.remove("logged")
        editor.apply()
    }

    fun clearLogin() {
        val sharedPreferences = mCtx.getSharedPreferences("login", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun clearId() {
        val sharedPreferences = mCtx.getSharedPreferences("id", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
    fun clearPassword() {
        val sharedPreferences = mCtx.getSharedPreferences("password", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }



}