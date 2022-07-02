package com.example.mvvmretrofit.Data

import com.example.mvvmretrofit.Model.Movie
import io.reactivex.rxjava3.core.Observable

class ApiHelper(private val apiService: ApiService) {
    fun getAllMovies(): Observable<List<Movie>> = apiService.getAllMovies()
}