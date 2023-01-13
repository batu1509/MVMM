package com.batueksi.tekrar.models

data class TvShow(
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)