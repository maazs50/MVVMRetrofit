package com.example.mvvmretrofit.Ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretrofit.LogUtils
import com.example.mvvmretrofit.Model.Movie
import com.example.mvvmretrofit.databinding.AdapterMovieBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var movies = mutableListOf<Movie>()

    fun setMoviesList(moviesList: List<Movie>){
        movies = moviesList.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(infalter,parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
         return movies.size
    }

    class MainViewHolder(val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(movie: Movie){
                binding.name.text = movie.name
                Glide.with(itemView.context).load(movie.imageUrl).into(binding.imageview)
            }
    }
}
