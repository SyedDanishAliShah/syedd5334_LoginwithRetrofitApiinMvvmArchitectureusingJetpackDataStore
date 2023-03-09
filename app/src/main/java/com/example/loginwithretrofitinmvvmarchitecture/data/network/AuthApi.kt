package com.example.loginwithretrofitinmvvmarchitecture.data.network

import com.example.loginwithretrofitinmvvmarchitecture.data.network.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded // As we are using Field annotation
    @POST("createUser")// url end point where retrofit will hit
    suspend fun login(
        @Field("email") email : String,//this annotation provides extra information about our post request
        @Field("password") password : String
    ) : LoginResponse//using suspend keyword with the function because we r using coroutines for our asynchronous calls
}