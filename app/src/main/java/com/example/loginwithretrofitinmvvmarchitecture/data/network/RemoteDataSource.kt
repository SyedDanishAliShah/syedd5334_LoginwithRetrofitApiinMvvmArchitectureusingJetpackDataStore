package com.example.loginwithretrofitinmvvmarchitecture.data.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://4efe27a0-ce3c-4dc0-9d53-9d84d3f2b61b.mock.pstmn.io/"

    }
    fun<Api> buildApi(
        api: Class<Api>
    ) : Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }//if we r running the debug version of the application then only we will add the http logging interceptor
                } .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    }
}