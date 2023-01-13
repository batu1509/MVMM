package com.batueksi.tekrar.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batueksi.tekrar.repository.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel
@Inject
constructor
    (private val repository: PopularMoviesRepository):ViewModel() {

    private val _response = MutableLiveData<List<com.batueksi.tekrar.models.Result>>()
    val responsePopularMovies: LiveData<List<com.batueksi.tekrar.models.Result>>
        get() = _response

    init {
        getAllPopularMovies()
    }

    private fun getAllPopularMovies() = viewModelScope.launch {
        repository.GetPopularMovies().let {response ->

            if (response.isSuccessful){
                _response.postValue(response.body()?.results)
            }else{
                Log.d("tag", "getAllTvShows Error: ${response.code()}")
            }
        }
    }

}