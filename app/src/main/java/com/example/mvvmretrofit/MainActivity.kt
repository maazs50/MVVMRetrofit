package com.example.mvvmretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.Data.ApiHelper
import com.example.mvvmretrofit.Data.RetrofitBuilder
import com.example.mvvmretrofit.Ui.MainAdapter
import com.example.mvvmretrofit.ViewModel.MainViewModel
import com.example.mvvmretrofit.ViewModel.MyViewModelFactory
import com.example.mvvmretrofit.coroutines.utils.Status
import com.example.mvvmretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        binding.recyclerview.adapter = adapter
        setUpObservers()
        apiCallwithViewModel()
    }

    fun apiCallwithViewModel(){
        viewModel.getAllMovies()
    }

    fun setUpViewModel(){
        viewModel = ViewModelProvider(this,
            MyViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)
    }

    fun setUpObservers(){
        viewModel.getAllMovies().observe(this, Observer {
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        resource.data?.let{
                            movies-> adapter.setMoviesList(movies)
                        }
                    }
                    Status.LOADING -> {
                        Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resource.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}