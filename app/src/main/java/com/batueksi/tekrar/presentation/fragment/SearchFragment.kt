package com.batueksi.tekrar.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.batueksi.tekrar.R
import com.batueksi.tekrar.databinding.ErrorLayoutBinding
import com.batueksi.tekrar.databinding.SearchFragmentBinding
import com.batueksi.tekrar.presentation.adapter.SearchAdapter
import com.batueksi.tekrar.presentation.adapter.SearchStateAdapter
import com.batueksi.tekrar.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private var _binding : SearchFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)

        searchAdapter = SearchAdapter{
            findNavController().navigate(R.id.action_searchFragment_to_detailsFragment, bundleOf("content_arg" to it.id))
        }

        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.requestFocus()


        binding.recyclerViewSearch.layoutManager = (GridLayoutManager(binding.root.context, 2));

        binding.recyclerViewSearch.adapter = searchAdapter.withLoadStateFooter(
            SearchStateAdapter {
                searchAdapter.retry()
            }
        )

        binding.swipeRefreshSearch.isEnabled = false
        searchAdapter.addLoadStateListener { state ->
            binding.swipeRefreshSearch.isRefreshing = state.source.refresh is LoadState.Loading
            binding.errorLayout.errorContainer.isVisible = state.source.refresh is LoadState.Error
            binding.recyclerViewSearch.isVisible = !binding.errorLayout.errorContainer.isVisible

            if (state.source.refresh is LoadState.Error) {
                binding.errorLayout.btnRetryError.setOnClickListener {
                    searchAdapter.retry()
                }

                val errorMessage = (state.source.refresh as LoadState.Error).error.message
                binding.errorLayout.textViewErrorMessageError.text = errorMessage
            }
        }



        binding.toolBarSearch.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }

            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText != "") {
                    viewModel.onSearchMovie(newText)
                    binding.recyclerViewSearch.visibility = View.VISIBLE
                } else {
                    binding.recyclerViewSearch.visibility = View.GONE
                }
                return false

            }
        })

        viewModel.searchMovies.observe(viewLifecycleOwner, {
            searchAdapter.submitData(lifecycle, it)
        })

    }


}

