package com.batueksi.tekrar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.batueksi.tekrar.adapter.ContentAdapter
import com.batueksi.tekrar.adapter.ListAdapter
import com.batueksi.tekrar.databinding.ActivityMainBinding
import com.batueksi.tekrar.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
//    private lateinit var contentAdapter: ContentAdapter
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.hide()
        setUpRv()
        observeData()
    }

    private fun observeData() {
        viewModel.liveData.observe(this) { contentList ->
            adapter.differ.submitList(contentList)
        }
    }

    private fun setUpRv(){
        adapter = ListAdapter()
        binding.recyclerviewLists.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
//        contentAdapter = ContentAdapter()

//        binding.recyclerView.apply {
//            adapter = popularMoviesAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
//
//            setHasFixedSize(true)
//        }
//
//        viewModel.responsePopularMovies.observe(this,{listPopularMovie->
//
//            popularMoviesAdapter.PopularMovies = listPopularMovie
//
//        })
    }
}