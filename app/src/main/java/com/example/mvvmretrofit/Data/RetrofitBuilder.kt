package com.example.mvvmretrofit.Data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//Singleton class
object RetrofitBuilder {
    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://howtodoandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}