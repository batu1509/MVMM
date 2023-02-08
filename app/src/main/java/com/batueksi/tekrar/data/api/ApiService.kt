package com.batueksi.tekrar.data.api

import com.batueksi.tekrar.data.models.PopularMovie
import com.batueksi.tekrar.data.models.TvShow
import com.batueksi.tekrar.data.models.UpComingMovies
import com.batueksi.tekrar.data.models.detailsmodel.MovieDetailsModel
import com.batueksi.tekrar.data.models.searchmodel.SearchModel
import com.batueksi.tekrar.data.models.tvshowdetailsmodel.TvShowDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun GetPopularMovies(@Query("api_key") apiKey: String): Response<PopularMovie>

    @GET("tv/popular")
    suspend fun GetPopularTvShows(@Query("api_key") apiKey: String): Response<TvShow>

    @GET("movie/upcoming")
    suspend fun GetUpComingMovies(@Query("api_key") apiKey: String): Response<UpComingMovies>

    @GET("movie/{movie_id}")
    suspend fun GetDetailsMovies(@Path("movie_id") movieId: String, @Query("api_key") apiKey: String): Response<MovieDetailsModel>

    @GET("tv/{tv_id}")
    suspend fun GetDetailsTvShows(@Path("tv_id") tvId: String, @Query("api_key") apiKey: String): Response<TvShowDetails>

    @GET("search/multi")
    suspend fun searchFilms(@Query("query") query: String = "", @Query("page") page: Int = 1, @Query("api_key") apiKey: String): Response<SearchModel>
}