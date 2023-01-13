package com.batueksi.tekrar.repository

import com.batueksi.tekrar.api.ApiService
import com.batueksi.tekrar.helper.Constants
import javax.inject.Inject

class PopularMoviesRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun GetPopularMovies() = apiService.GetPopularMovies(Constants.apikey)
}