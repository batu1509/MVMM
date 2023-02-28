package com.batueksi.tekrar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.batueksi.tekrar.data.api.ApiService
import com.batueksi.tekrar.data.models.searchmodel.Search
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: ApiService,
) {


    @ExperimentalCoroutinesApi
    fun getSearchMovies(query: String): Flowable<PagingData<Search>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).flowable
    }
}