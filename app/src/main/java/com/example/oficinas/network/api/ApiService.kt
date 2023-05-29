package com.example.oficinas.network.api

import com.example.oficinas.network.models.data.CheckListViewModel
import com.example.oficinas.network.models.login.LoginRequest
import com.example.oficinas.network.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth")
    fun login(
        @Body login: LoginRequest
    ): Call<LoginResponse>

    @GET("Checklists/authorized/{id}")
    fun getCheckListAutorized(
        @Header("Authorization") bearer:String,
        @Path("id") type: String
    ): Call<CheckListViewModel>

    @GET("Checklists/anonymous/{id}")
    fun getCheckListAnonymous(
        @Path("id") id: String
    ): Call<CheckListViewModel>

    @FormUrlEncoded
    @POST("Checklists/authorized/{path}")
    fun sendResponse(
        @Header("Authorization") bearer:String,
        @Path("path") path: String,
        @Field("id") id:String,
        @Field("response") response: Boolean?,
        @Field("responseOptions") responseOptions:ArrayList<String>?,
        @Field("text") text:String?,
        @Field("commentText") commentText:String?

        ): Call<ResponseBody>
}