package com.example.loginwithretrofitinmvvmarchitecture.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value : T) : Resource<T>()
    data class Failure(
        val isNetworkError : Boolean,//will be true when we have a network error
        val errorCode : Int?,//to get the actual error code when we dont have a network error
        val errorBody: ResponseBody?

    ) : Resource<Nothing>()
}//class made to wrap the api resposne