package com.example.mvvmretrofit.repo

import com.example.mvvmretrofit.Data.ApiHelper

class MainRepository constructor(private val apiHelper: ApiHelper) {
    suspend fun getAllMovies() = apiHelper.getAllMovies()
}