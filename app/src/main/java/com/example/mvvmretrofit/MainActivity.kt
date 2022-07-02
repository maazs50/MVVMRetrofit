package com.example.mvvmretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.Data.RetrofitService
import com.example.mvvmretrofit.Ui.MainAdapter
import com.example.mvvmretrofit.ViewModel.MainViewModel
import com.example.mvvmretrofit.ViewModel.MyViewModelFactory
import com.example.mvvmretrofit.databinding.ActivityMainBinding
import com.example.mvvmretrofit.repo.MainRepository

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService= RetrofitService.getInstance()
    val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository((retrofitService))))
            .get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(this, Observer {
            adapter.setMoviesList(it)
        })
        viewModel.errorMessage.observe(this, Observer {

        })
        apiCallwithViewModel()
    }

    fun apiCallwithViewModel(){
        viewModel.getAllMovies()
    }

}