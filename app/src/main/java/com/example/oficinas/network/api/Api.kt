package com.example.oficinas.network.api


import com.example.oficinas.network.models.login.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @Headers("Content-Type:application/json")
    @POST("/auth")
    fun login(
        @Body auth: LoginRequest
    ): Call<ResponseBody>

    @GET("api/utils/company")
    fun getCompany(

    ): Call<ResponseBody>

    @GET("api/sales/documents/{type}/{numberdoc}?print=A")
    fun getArticlesPdf(
        @Path("type") type: String,
        @Path("numberdoc") number: String
    ): Call<ResponseBody>

    @GET("api/sales/documents/{type}/{numberdoc}?print=A")
    fun getArticlesImages(
        @Path("type") type: String,
        @Path("numberdoc") number: String,
        @Query("images") image: String
    ): Call<ResponseBody>

    @GET("api/entities/customers/{id}")
    fun getUser(
        @Path("id") plate: String
    ): Call<ResponseBody>

    @GET("api/workshop/os/{numberDoc}/images")
    fun imagesOS(
        @Path("numberDoc") plate: String
    ): Call<ResponseBody>


    @GET("api/workshop/vehicles/")
    fun listVehicles(
        @Query("filter[customerowner]") customerOwner: String,
        @Query("operator") operator: String,
        @Query("NEW") new: String,
        @Query("FULL") full: String
    ): Call<ResponseBody>


    @GET("api/workshop/vehicles/{plate}")
    fun listVehiclesDetails(
        @Path("plate") plate: String
    ): Call<ResponseBody>

    @GET("api/workshop/os")
    fun listInterventionDetails(
        @Query("filter[plate]") car_number: String,
        @Query("operator") operator: String,
        @Query("NEW") new: String,
        @Query("FULL") full: String,
        @Query("INTERVENTIONS") intervention: String
    ): Call<ResponseBody>

    @GET("api/workshop/os/{plate}")
    fun listObs(
        @Path("plate") plate: String
    ): Call<ResponseBody>

    @GET("api/workshop/interventions")
    fun listInterventionSchedule(

    ): Call<ResponseBody>

    @GET("api/workshop/vehicles_articles/{plate}")
    fun listVehiclesArticles(
        @Path("plate") plate: String,
        @Query("filter[actualizacc]") actualizacc: String,
        @Query("group_articles") group_articles: String
    ): Call<ResponseBody>


    @GET("api/workshop/os/")
    fun listIntervention(
        @Query("filter[idcustomer]") idcustomer: String,
        @Query("operator") operator: String,
        @Query("NEW") new: String,
        @Query("FULL") full: String,
        @Query("INTERVENTIONS") intervention: String
    ): Call<ResponseBody>

    @GET("api/entities/customers/{id_client}/movements")
    fun listDocuments(
        @Path("id_client") customerOwner: String,
    ): Call<ResponseBody>

    @GET("api/sales/documents/{type}/{numberdoc}?print=A")
    fun downloadPDF(
        @Path("type") type: String,
        @Path("numberdoc") number: String
    ): Call<ResponseBody>


    @GET("/api/workshop/alerts/{operator}")
    fun getAlerts(
        @Path("operator") type: String,
    ): Call<ResponseBody>







    @GET("api/crm/appointments")
    fun userAppointments(
        @Query("filter[entitycode]") entitycode: String,
        @Query("filter[entitytype]") entitytype: String,


    ): Call<ResponseBody>

    @GET("api/sales/documents/{type}")
    fun getBudget(
        @Path("type") type: String,
        @Query("filter[state]") entitytype: String,
        @Query("filter[idcustomer]") idcustomer: String
    ): Call<ResponseBody>


    @GET("api/sales/documents/or/{number}/status")
    fun getStatus(
        @Path("number") number: String,

    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api/sales/documents/or/{number}/status")
    fun setStatus(
        @Path("number") number: String,
        @Field("status") status: String,
        ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api/utils/send_email")
    fun setEmail(
        @Field("to") to: String,
        @Field("subject") subject: String,
        @Field("content") content: String,
    ): Call<ResponseBody>


}