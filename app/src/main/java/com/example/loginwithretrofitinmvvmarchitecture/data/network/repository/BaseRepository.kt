package com.example.loginwithretrofitinmvvmarchitecture.data.network.repository

import com.example.loginwithretrofitinmvvmarchitecture.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall : suspend () -> T
    ) : Resource<T> {
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())//if the call was successfull so the invoke function will provide us the result directly and we will put the result in our resource sealed class
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }

        }//using dispatchers.io as not to block the main ui thread and run the coroutine in a separate thread.


    }//this function will execute the api call


}