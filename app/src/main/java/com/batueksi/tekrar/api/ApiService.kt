package com.batueksi.tekrar.api

import com.batueksi.tekrar.models.PopularMovie
import com.batueksi.tekrar.models.TvShow
import com.batueksi.tekrar.models.UpComingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun GetPopularMovies(@Query("api_key") apiKey: String): Response<PopularMovie>

    @GET("tv/popular")
    suspend fun GetPopularTvShows(@Query("api_key") apiKey: String): Response<TvShow>

    @GET("movie/upcoming")
    suspend fun GetUpComingMovies(@Query("api_key") apiKey: String): Response<UpComingMovies>

}