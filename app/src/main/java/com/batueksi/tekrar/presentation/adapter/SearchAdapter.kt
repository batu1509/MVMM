package com.batueksi.tekrar.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.batueksi.tekrar.R
import com.batueksi.tekrar.data.models.searchmodel.Search
import com.batueksi.tekrar.databinding.RecyclerRowBinding
import com.bumptech.glide.Glide


class SearchAdapter : PagingDataAdapter<Search, SearchAdapter.SearchViewHolder>(diffcallback) {

//    private var onItemClickListener: ((Search)->Unit)? = null
//
//    fun setOnItemClickListener(listener: (Search)-> Unit){
//        onItemClickListener = listener
//    }

    inner class SearchViewHolder(private val binding: RecyclerRowBinding) : ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.apply {
            binding.textView.text = search.title
            Glide.with(imageView).load("https://image.tmdb.org/t/p/w500${search.poster_path}")
                .placeholder(R.drawable.ic_image)
                .into(imageView)
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
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search = getItem(position)
        search?.let { holder.bind(it) }
    }

}