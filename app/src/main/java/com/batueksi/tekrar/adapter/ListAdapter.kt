package com.batueksi.tekrar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.batueksi.tekrar.databinding.ListRowBinding
import com.batueksi.tekrar.models.ContentList

class ListAdapter : Adapter<ListAdapter.ListViewHolder>(){

    private val diffcalback = object : DiffUtil.ItemCallback<ContentList>(){
        override fun areItemsTheSame(oldItem: ContentList, newItem: ContentList): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ContentList, newItem: ContentList): Boolean {
            return newItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this, diffcalback)

    inner class ListViewHolder(private val binding: ListRowBinding): ViewHolder(binding.root) {
        fun bind(list: ContentList) {
            binding.listTitle.text = list.title
            val contentAdapter = ContentAdapter()
            contentAdapter.differ.submitList(list.contents)
            binding.recyclerviewMovies.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            binding.recyclerviewMovies.adapter = contentAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = differ.currentList[position]
        holder.bind(list)
    }

    override fun getItemCount(): Int = differ.currentList.size

}