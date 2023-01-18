package com.batueksi.tekrar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batueksi.tekrar.models.ContentList
import com.batueksi.tekrar.models.detailsmodel.MovieDetailsModel
import com.batueksi.tekrar.repository.ContentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: ContentsRepository):ViewModel() {

    private val detaildata = MutableLiveData<List<MovieDetailsModel>>()



}