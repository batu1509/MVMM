package com.batueksi.tekrar.data.models

data class PopularMovie(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)