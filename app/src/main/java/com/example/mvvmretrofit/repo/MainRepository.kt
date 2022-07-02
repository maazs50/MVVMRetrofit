package com.example.mvvmretrofit.repo

import com.example.mvvmretrofit.Data.ApiHelper
import com.example.mvvmretrofit.Model.Movie
import io.reactivex.rxjava3.core.Observable

class MainRepository constructor(private val apiHelper: ApiHelper) {
    fun getAllMovies(): Observable<List<Movie>> = apiHelper.getAllMovies()
}