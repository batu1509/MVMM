package com.batueksi.tekrar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batueksi.tekrar.R
import com.batueksi.tekrar.databinding.ItermMovieFooterStateBinding
import com.batueksi.tekrar.databinding.RecyclerRowBinding


class MovieFooterStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieFooterStateAdapter.MovieFooterStateViewHolder>() {

    override fun onBindViewHolder(holder: MovieFooterStateViewHolder, loadState: LoadState) { holder.bind(loadState) }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MovieFooterStateViewHolder { return MovieFooterStateViewHolder.create(parent, retry) }

    class MovieFooterStateViewHolder private constructor(private val binding: ItermMovieFooterStateBinding,  retry: () -> Unit) :
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
            fun create(parent: ViewGroup, retry: () -> Unit): MovieFooterStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.iterm_movie_footer_state, parent, false)
                val binding = ItermMovieFooterStateBinding.inflate(LayoutInflater.from(parent.context),parent,false)

                return MovieFooterStateViewHolder(binding, retry)
            }
        }
    }
}