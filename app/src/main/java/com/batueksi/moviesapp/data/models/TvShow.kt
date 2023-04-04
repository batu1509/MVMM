package com.batueksi.moviesapp.data.models

data class TvShow(
    val page: Int,
    val results: List<com.batueksi.moviesapp.data.models.ResultXX>,
    val total_pages: Int,
    val total_results: Int
)