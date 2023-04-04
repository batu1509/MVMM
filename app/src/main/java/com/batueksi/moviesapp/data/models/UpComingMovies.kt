package com.batueksi.moviesapp.data.models

data class UpComingMovies(
    val dates: com.batueksi.moviesapp.data.models.Dates,
    val page: Int,
    val results: List<com.batueksi.moviesapp.data.models.Result>,
    val total_pages: Int,
    val total_results: Int
)