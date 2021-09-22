package com.manuelsoft.supermovies2.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    companion object {
        fun getRetrofitService(baseUrl : String) : RetrofitApi {
            return RetrofitProvider()
                .buildRetrofit(baseUrl)
                .create(RetrofitApi::class.java)
        }
    }

    fun buildRetrofit(baseUrl : String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()
    }

    private fun getHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }





}