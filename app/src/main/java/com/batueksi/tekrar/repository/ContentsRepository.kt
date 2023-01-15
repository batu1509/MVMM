package com.batueksi.tekrar.repository

import com.batueksi.tekrar.api.ApiService
import com.batueksi.tekrar.helper.Constants
import com.batueksi.tekrar.models.ContentList
import com.batueksi.tekrar.models.ResultXX
import com.batueksi.tekrar.util.toContentList
import com.batueksi.tekrar.util.toContentList1
import javax.inject.Inject

class ContentsRepository @Inject constructor(private val apiService: ApiService) {

    private suspend fun getPopularMovies(): List<com.batueksi.tekrar.models.Result> {
        val response = apiService.GetPopularMovies(Constants.apikey)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    private suspend fun getPopularTvShows(): List<ResultXX> {
        val response = apiService.GetPopularTvShows(Constants.apikey)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    private suspend fun getUpcomingMovies(): List<com.batueksi.tekrar.models.Result> {
        val response = apiService.GetUpComingMovies(Constants.apikey)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    suspend fun getAllLists(): List<ContentList> {
        val popularMovies = getPopularMovies()
        val tvShows = getPopularTvShows()
        val upComingMovies = getUpcomingMovies()
        val popularList = popularMovies.toContentList("Popular Movies")
        val tvShowList = tvShows.toContentList1("TV Shows")
        val upcomingList = upComingMovies.toContentList("Upcoming Movies")
        return listOf(popularList, tvShowList, upcomingList)
    }

}