package com.batueksi.tekrar.data.models

data class UpComingMovies(
    val dates: com.batueksi.tekrar.data.models.Dates,
    val page: Int,
    val results: List<com.batueksi.tekrar.data.models.Result>,
    val total_pages: Int,
    val total_results: Int
)