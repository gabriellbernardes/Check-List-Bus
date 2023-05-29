package com.example.oficinas.network.models.login

import com.google.gson.annotations.SerializedName

class Token(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiration: Long

)