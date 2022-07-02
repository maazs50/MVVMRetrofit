package com.example.mvvmretrofit.Data

import com.example.mvvmretrofit.Model.Movie
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    suspend fun getAllMovies(): List<Movie>
}