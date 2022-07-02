package com.example.mvvmretrofit.Data

import com.example.mvvmretrofit.Model.Movie
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    //Endpoints
    @GET("movielist.json")
    fun getAllMovies(): Observable<List<Movie>>
}