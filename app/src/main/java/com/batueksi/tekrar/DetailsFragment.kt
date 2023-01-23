package com.batueksi.tekrar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.batueksi.tekrar.adapter.ContentAdapter
import com.batueksi.tekrar.adapter.ListAdapter
import com.batueksi.tekrar.databinding.FragmentDetailsBinding
import com.batueksi.tekrar.models.Content
import com.batueksi.tekrar.models.ContentList
import com.batueksi.tekrar.models.detailsmodel.Genre
import com.batueksi.tekrar.models.detailsmodel.MovieDetailsModel
import com.batueksi.tekrar.viewmodel.DetailsViewModel
import com.batueksi.tekrar.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding

    private val viewModel: DetailsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsId = arguments?.getInt("content_arg")
        viewModel.getMovieById(detailsId.toString())
        viewModel.detaildata.observe(viewLifecycleOwner) {moviemodel->
            binding.detailsViewTitle.text = moviemodel.title
            binding.detailsViewDates.text = moviemodel.release_date
            binding.detailsViewDescription.text = moviemodel.overview
            binding.detailsImageView.load("https://image.tmdb.org/t/p/w500${moviemodel.backdrop_path}"){
                crossfade(true)
                crossfade(1000)
            }
        }
        viewModel.getTvShowById(detailsId.toString())
        viewModel.tvdetaildata.observe(viewLifecycleOwner){tvmodel->
            binding.detailsViewTitle.text = tvmodel.name
            binding.detailsViewDates.text = tvmodel.last_air_date
            binding.detailsViewDescription.text = tvmodel.overview
            binding.detailsImageView.load("https://image.tmdb.org/t/p/w500${tvmodel.backdrop_path}"){
                crossfade(true)
                crossfade(1000)
            }
        }

    }

}


