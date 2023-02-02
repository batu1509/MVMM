package com.batueksi.tekrar.data.models

data class TvShow(
    val page: Int,
    val results: List<com.batueksi.tekrar.data.models.ResultXX>,
    val total_pages: Int,
    val total_results: Int
)