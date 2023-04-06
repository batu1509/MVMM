package com.batueksi.moviesapp.data.repository

import com.batueksi.moviesapp.BuildConfig
import com.batueksi.moviesapp.data.api.ApiService
import com.batueksi.moviesapp.helper.Constants
import com.batueksi.moviesapp.data.models.ContentList
import com.batueksi.moviesapp.data.models.ResultXX
import com.batueksi.moviesapp.data.models.detailsmodel.MovieDetailsModel
import com.batueksi.moviesapp.data.models.tvshowdetailsmodel.TvShowDetails
import com.batueksi.moviesapp.data.models.videomodel.MovieVideo
import com.batueksi.moviesapp.util.toContentList
import com.batueksi.moviesapp.util.toContentList1
import javax.inject.Inject

class ContentsRepository @Inject constructor(private val apiService: ApiService) {

    private suspend fun getPopularMovies(): List<com.batueksi.moviesapp.data.models.Result> {
        val response = apiService.GetPopularMovies(BuildConfig.API_KEY)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    private suspend fun getPopularTvShows(): List<ResultXX> {
        val response = apiService.GetPopularTvShows(BuildConfig.API_KEY)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }

    private suspend fun GetTopRatedTvShows(): List<ResultXX> {
        val response = apiService.GetTopRatedTvShows(BuildConfig.API_KEY)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }


    private suspend fun getUpcomingMovies(): List<com.batueksi.moviesapp.data.models.Result> {
        val response = apiService.GetUpComingMovies(BuildConfig.API_KEY)
        return if (response.body() != null)
            response.body()!!.results
        else
            emptyList()
    }


    suspend fun getDetailsMovies(movieId: String): MovieDetailsModel? {
        val response = apiService.GetDetailsMovies(movieId, BuildConfig.API_KEY)
        if (response.body() != null) {
            return response.body()!!
        }
        return null
    }


    suspend fun getDetailsTvShows(tvId: String): TvShowDetails? {
        val response = apiService.GetDetailsTvShows(tvId, BuildConfig.API_KEY)
        if (response.body() != null) {
            return response.body()!!
        }
        return null
    }

    suspend fun getVideosMovie(movieId: String): MovieVideo? {
        val response = apiService.GetMovieVideos(movieId, BuildConfig.API_KEY)
        if (response.body() != null) {
            return response.body()!!
        }
        return null
    }


    suspend fun getAllLists(): List<ContentList> {
        val popularMovies = getPopularMovies()
        val tvShows = getPopularTvShows()
        val upComingMovies = getUpcomingMovies()
        val tvShowsTopRated = GetTopRatedTvShows()
        val popularList = popularMovies.toContentList("Popular Movies")
        val tvShowList = tvShows.toContentList1("TV Shows")
        val tvShowTopRatedList = tvShowsTopRated.toContentList1("Latest Tv Shows")
        val upcomingList = upComingMovies.toContentList("Upcoming Movies")
        return listOf(popularList, tvShowList, upcomingList, tvShowTopRatedList)
    }


}