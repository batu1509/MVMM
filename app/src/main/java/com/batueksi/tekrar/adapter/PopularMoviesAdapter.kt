package com.batueksi.tekrar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.batueksi.tekrar.databinding.RecyclerRowBinding
import com.batueksi.tekrar.models.Result

class PopularMoviesAdapter: RecyclerView.Adapter<PopularMoviesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerRowBinding):
        ViewHolder(binding.root)


    private val diffcalback = object : DiffUtil.ItemCallback<com.batueksi.tekrar.models.Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffcalback)
    var PopularMovies: List<com.batueksi.tekrar.models.Result>
    get() = differ.currentList
    set(value){
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPopularMovies = PopularMovies[position]
        holder.binding.apply {
            textView.text =  currentPopularMovies.title
            imageView.load("https://image.tmdb.org/t/p/w500${currentPopularMovies.backdrop_path}"){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount()= PopularMovies.size


}