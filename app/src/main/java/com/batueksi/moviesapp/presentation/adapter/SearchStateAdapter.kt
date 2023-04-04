package com.batueksi.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batueksi.moviesapp.R
import com.batueksi.moviesapp.databinding.ItermMovieFooterStateBinding


class SearchStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<SearchStateAdapter.SearchStateViewHolder>() {

    override fun onBindViewHolder(holder: SearchStateViewHolder, loadState: LoadState) { holder.bind(loadState) }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): SearchStateViewHolder { return SearchStateViewHolder.create(parent, retry) }

    class SearchStateViewHolder private constructor(private val binding: ItermMovieFooterStateBinding,  retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetryFooter.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Error) {
                    textViewErrorFooter.text = loadState.error.message
                }

                progressBarFooter.isVisible = loadState is LoadState.Loading
                btnRetryFooter.isVisible = loadState !is LoadState.Loading
                textViewErrorFooter.isVisible = loadState !is LoadState.Loading
            }
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): SearchStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.iterm_movie_footer_state, parent, false)
                val binding = ItermMovieFooterStateBinding.inflate(LayoutInflater.from(parent.context),parent,false)

                return SearchStateViewHolder(binding, retry)
            }
        }
    }
}