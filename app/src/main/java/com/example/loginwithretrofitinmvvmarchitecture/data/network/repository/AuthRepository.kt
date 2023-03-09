package com.example.loginwithretrofitinmvvmarchitecture.data.network.repository

import com.example.loginwithretrofitinmvvmarchitecture.data.network.AuthApi
import com.example.loginwithretrofitinmvvmarchitecture.data.network.UserPreferences

class AuthRepository(
    private val api : AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email : String,
        password : String

    ) = safeApiCall {
        api.login(email, password)
    }//this function will hit the login api

    suspend fun saveAuthToken(token : String){
        preferences.saveAuthToken(token)
    }
}