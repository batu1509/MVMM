package com.batueksi.tekrar.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.batueksi.tekrar.R
import com.batueksi.tekrar.databinding.FragmentDetailsBinding
import com.batueksi.tekrar.presentation.viewmodel.DetailsViewModel
import com.batueksi.tekrar.util.YoutubePlay
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
        setupToolbar()

        binding.fabPlay.setOnClickListener {
            if (viewModel.movievideodata.value != null && viewModel.movievideodata.value!!.results.isNotEmpty()) {
                val video = YoutubePlay(viewModel.movievideodata.value!!.results.last().key)
                video.show(childFragmentManager, "Video")
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.trailer_not_availabe),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsId = arguments?.getInt("content_arg")
        viewModel.getMovieById(detailsId.toString())
        viewModel.getVideoById(detailsId.toString())
        viewModel.detaildata.observe(viewLifecycleOwner) {moviemodel->
            binding.detailsViewTitle.text = moviemodel.title
            binding.detailsViewDates.text = moviemodel.release_date
            binding.detailsViewDescription.text = moviemodel.overview
            binding.detailsDuration.text = handleRuntimeMovie(moviemodel.runtime)
            binding.ratingBar.rating = moviemodel.vote_average.toFloat()
            var genres = ""
            for (i in moviemodel.genres) {
                genres += i.name + " "
            }
            binding.genre.text = genres
            binding.detailsVoteAverage.text = String.format("%.2f", moviemodel.vote_average)
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
            binding.ratingBar.rating = tvmodel.vote_average.toFloat()
            var genres = ""
            for (i in tvmodel.genres) {
                genres += i.name + " "
            }
            binding.genre.text = genres
            binding.detailsVoteAverage.text = String.format("%.2f", tvmodel.vote_average)
            binding.detailsImageView.load("https://image.tmdb.org/t/p/w500${tvmodel.backdrop_path}"){
                crossfade(true)
                crossfade(1000)
            }
        }

    }


    private fun setupToolbar() {
        binding.genericToolbar.apply {
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun handleRuntimeMovie(runtime: Int?): String {
        if (runtime == null) return "-"

        val time = runtime / 60.0
        return String.format("%.2fhr", time)
    }
}


