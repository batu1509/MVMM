package com.batueksi.tekrar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.batueksi.tekrar.adapter.ListAdapter
import com.batueksi.tekrar.databinding.FragmentDetailsBinding
import com.batueksi.tekrar.models.Content
import com.batueksi.tekrar.viewmodel.MainViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding
    private lateinit var adapter: ListAdapter
    private val viewModel: MainViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)


        return binding.root

    }

//    private fun observeData() {
//        viewModel.liveData.observe(viewLifecycleOwner) { contentList ->
//            adapter.differ.submitList(contentList)
//        }
//    }
//
//    private fun setUpRv() {
//        adapter = ListAdapter()
//        binding.apply {
//            adapter = this@DetailsFragment.adapter
//        }
    }


