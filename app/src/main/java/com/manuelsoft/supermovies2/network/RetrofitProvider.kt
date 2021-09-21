package com.manuelsoft.supermovies2.network

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
            .build()
    }





}