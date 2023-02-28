package com.batueksi.tekrar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.batueksi.tekrar.data.models.searchmodel.Search
import com.batueksi.tekrar.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _searchMovies = MutableLiveData<PagingData<Search>>()
    val searchMovies: LiveData<PagingData<Search>>
        get() {
            return _searchMovies
        }

    override fun onCleared() {
        super.onCleared()
        // fix memory leak
        compositeDisposable.clear()
    }

    @ExperimentalCoroutinesApi
    fun onSearchMovie(query: String) {
        compositeDisposable.add(
            repository.getSearchMovies( query )
                .cachedIn(viewModelScope)
                .subscribe {
                    _searchMovies.value = it
                }
        )
    }
}