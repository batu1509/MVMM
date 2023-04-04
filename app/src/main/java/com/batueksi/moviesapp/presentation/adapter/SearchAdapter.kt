package com.batueksi.moviesapp.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.batueksi.moviesapp.R
import com.batueksi.moviesapp.data.models.searchmodel.Search
import com.batueksi.moviesapp.databinding.ItemMovieBinding
import com.bumptech.glide.Glide


class SearchAdapter(val onItemClick: (Search) -> Unit) : PagingDataAdapter<Search, SearchAdapter.SearchViewHolder>(diffcallback) {

    inner class SearchViewHolder(private val binding: ItemMovieBinding, val onItemClick: (Search) -> Unit) : ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.apply {
            binding.textView.text = search.title
            Glide.with(imageView).load("https://image.tmdb.org/t/p/w500${search.poster_path}")
                .placeholder(R.drawable.ic_image)
                .into(imageView)

                binding.root.setOnClickListener {
                    onItemClick(search)
                }
            }
        }
    }

    companion object {
        private val diffcallback = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding, onItemClick)
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search = getItem(position)
        search?.let { holder.bind(it) }
    }

}