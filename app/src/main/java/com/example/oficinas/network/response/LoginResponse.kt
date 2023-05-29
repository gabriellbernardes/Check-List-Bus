package com.example.oficinas.network.response


import com.example.oficinas.network.models.login.Device
import com.example.oficinas.network.models.login.Token
import com.example.oficinas.network.models.login.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    val token: Token,
    val user: User,
    val device: Device,
    val message: String

)










