package com.batueksi.tekrar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batueksi.tekrar.data.models.detailsmodel.MovieDetailsModel
import com.batueksi.tekrar.data.models.tvshowdetailsmodel.TvShowDetails
import com.batueksi.tekrar.data.models.videomodel.MovieVideo
import com.batueksi.tekrar.data.repository.ContentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: ContentsRepository):ViewModel() {

    private val _detaildata = MutableLiveData<MovieDetailsModel>()
    val detaildata : LiveData<MovieDetailsModel> = _detaildata

    private val _tvdetaildata = MutableLiveData<TvShowDetails>()
    val tvdetaildata : MutableLiveData<TvShowDetails> = _tvdetaildata

    private val _movievideodata = MutableLiveData<MovieVideo>()
    val movievideodata : MutableLiveData<MovieVideo> = _movievideodata


    fun getMovieById(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getDetailsMovies(movieId)
            if (result != null)
                _detaildata.postValue(result!!)
        }
    }

    fun getTvShowById(tvId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getDetailsTvShows(tvId)
            if (result != null)
                _tvdetaildata.postValue(result!!)
        }
    }

    fun getVideoById(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getVideosMovie(movieId)
            if (result != null)
                _movievideodata.postValue(result!!)
        }
    }

    fun getTvVideoById(tvId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getVideosTv(tvId)
            if (result != null)
                _movievideodata.postValue(result!!)
        }
    }

}