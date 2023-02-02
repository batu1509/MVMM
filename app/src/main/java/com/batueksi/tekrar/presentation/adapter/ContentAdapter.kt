package com.batueksi.tekrar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.batueksi.tekrar.databinding.RecyclerRowBinding
import com.batueksi.tekrar.data.models.Content

class ContentAdapter(val onItemClick: (com.batueksi.tekrar.data.models.Content) -> Unit): RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerRowBinding, val onItemClick: (com.batueksi.tekrar.data.models.Content) -> Unit): ViewHolder(binding.root) {
        fun bind(content: com.batueksi.tekrar.data.models.Content) {
            binding.apply {
                textView.text =  content.title
                textView2.text = content.vote_avarage.toString()
                imageView.load("https://image.tmdb.org/t/p/w500${content.imagePath}"){
                    crossfade(true)
                    crossfade(1000)
                }
                binding.root.setOnClickListener {
                    onItemClick(content)
                }
            }
        }
    }

    private val diffcalback = object : DiffUtil.ItemCallback<com.batueksi.tekrar.data.models.Content>(){
        override fun areItemsTheSame(oldItem: com.batueksi.tekrar.data.models.Content, newItem: com.batueksi.tekrar.data.models.Content): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: com.batueksi.tekrar.data.models.Content, newItem: com.batueksi.tekrar.data.models.Content): Boolean {
            return newItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this, diffcalback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val content = differ.currentList[position]
        holder.bind(content)
    }

    override fun getItemCount()= differ.currentList.size


}