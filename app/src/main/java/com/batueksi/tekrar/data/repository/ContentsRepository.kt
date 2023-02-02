package com.batueksi.tekrar.data.repository

import com.batueksi.tekrar.data.api.ApiService
import com.batueksi.tekrar.helper.Constants
import com.batueksi.tekrar.data.models.ContentList
import com.batueksi.tekrar.data.models.ResultXX
import com.batueksi.tekrar.data.models.detailsmodel.Genre
import com.batueksi.tekrar.data.models.detailsmodel.MovieDetailsModel
import com.batueksi.tekrar.data.models.tvshowdetailsmodel.TvShowDetails
import com.batueksi.tekrar.util.toContentList
import com.batueksi.tekrar.util.toContentList1
import java.sql.RowId
import javax.inject.Inject

class ContentsRepository @Inject constructor(private val apiService: ApiService) {

    private suspend fun getPopularMovies(): List<com.batueksi.tekrar.data.models.Result> {
        val response = apiService.GetPopularMovies(Constants.apikey)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    private suspend fun getPopularTvShows(): List<com.batueksi.tekrar.data.models.ResultXX> {
        val response = apiService.GetPopularTvShows(Constants.apikey)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    private suspend fun getUpcomingMovies(): List<com.batueksi.tekrar.data.models.Result> {
        val response = apiService.GetUpComingMovies(Constants.apikey)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }


    suspend fun getDetailsMovies(movieId: String): com.batueksi.tekrar.data.models.detailsmodel.MovieDetailsModel? {
        val response = apiService.GetDetailsMovies(movieId, Constants.apikey)
        if (response.body() != null) {
            return response.body()!!
        }
        return null
    }

    suspend fun getDetailsTvShows(tvId: String): com.batueksi.tekrar.data.models.tvshowdetailsmodel.TvShowDetails? {
        val response = apiService.GetDetailsTvShows(tvId, Constants.apikey)
        if (response.body() != null) {
            return response.body()!!
        }
        return null
    }



    suspend fun getAllLists(): List<com.batueksi.tekrar.data.models.ContentList> {
        val popularMovies = getPopularMovies()
        val tvShows = getPopularTvShows()
        val upComingMovies = getUpcomingMovies()
        val popularList = popularMovies.toContentList("Popular Movies")
        val tvShowList = tvShows.toContentList1("TV Shows")
        val upcomingList = upComingMovies.toContentList("Upcoming Movies")
        return listOf(popularList, tvShowList, upcomingList)
    }

}