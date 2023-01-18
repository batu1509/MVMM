package com.batueksi.tekrar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.batueksi.tekrar.databinding.RecyclerRowBinding
import com.batueksi.tekrar.models.Content

class ContentAdapter: RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerRowBinding): ViewHolder(binding.root) {
        fun bind(content: Content) {
            binding.apply {
                textView.text =  content.title
                textView2.text = content.vote_avarage.toString()
                imageView.load("https://image.tmdb.org/t/p/w500${content.imagePath}"){
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    private val diffcalback = object : DiffUtil.ItemCallback<Content>(){
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return newItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this, diffcalback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val content = differ.currentList[position]
        holder.bind(content)
    }

    override fun getItemCount()= differ.currentList.size


}