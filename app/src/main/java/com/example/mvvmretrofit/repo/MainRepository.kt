package com.example.mvvmretrofit.repo

import com.example.mvvmretrofit.Data.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}