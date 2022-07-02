package com.example.mvvmretrofit.Data

class ApiHelper(private val apiService: ApiService) {
        suspend fun getAllMovies() = apiService.getAllMovies()
}