package com.example.oficinas.network.models.login


data class LoginRequest(
    var username : String = "",
    var password : String = "",
    var position: Position = Position(),
    var window:Int = 0,
    var device:Int = 0

)
