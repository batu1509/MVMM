package com.batueksi.moviesapp.presentation.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.batueksi.moviesapp.R
import com.batueksi.moviesapp.presentation.adapter.ListAdapter
import com.batueksi.moviesapp.databinding.FragmentHomeBinding
import com.batueksi.moviesapp.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpRv()
        observeData()

        return binding.root

    }

    private fun observeData() {
        viewModel.liveData.observe(viewLifecycleOwner) { contentList ->
            adapter.differ.submitList(contentList)
        }

    }

    private fun setUpRv() {
        adapter = ListAdapter{Content ->
            findNavController().navigate(R.id.action_homeFragment2_to_detailsFragment, bundleOf("content_arg" to Content.id))
        }
        binding.recyclerviewLists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HomeFragment.adapter
        }
    }
}

