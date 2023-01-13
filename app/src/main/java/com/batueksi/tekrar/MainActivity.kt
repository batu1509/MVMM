package com.batueksi.tekrar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.batueksi.tekrar.adapter.PopularMoviesAdapter
import com.batueksi.tekrar.databinding.ActivityMainBinding
import com.batueksi.tekrar.models.PopularMovie
import com.batueksi.tekrar.viewmodel.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: PopularMoviesViewModel by viewModels()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRv()
    }

    private fun setUpRv(){
        popularMoviesAdapter = PopularMoviesAdapter()

        binding.recyclerView.apply {
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

            setHasFixedSize(true)
        }

        viewModel.responsePopularMovies.observe(this,{listPopularMovie->

            popularMoviesAdapter.PopularMovies = listPopularMovie

        })
    }
}