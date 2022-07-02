package com.example.mvvmretrofit.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmretrofit.Model.Movie
import com.example.mvvmretrofit.Utils.Resource
import com.example.mvvmretrofit.repo.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repo: MainRepository): ViewModel() {
    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies(){
        val response = repo.getAllMovies()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMoviesListObserver())
    }

    private fun getMoviesListObserver(): Observer<List<Movie>> {
        return object : Observer<List<Movie>> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                Resource.error(data = null, message = e.message ?: "Error Occurred!")
                errorMessage.postValue(e.toString())
            }

            override fun onNext(t: List<Movie>) {
                Resource.success(t)
                movieList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
                Resource.loading(data = null)
            }
        }
    }

    fun <T> Observable<T>.toLiveData() : LiveData<T> =
        MutableLiveData<T>().apply {
            this@toLiveData.subscribe { value = it }
        }
}