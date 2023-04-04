package com.batueksi.moviesapp.data.models.searchmodel

data class SearchModel(
    val page: Int = 0,
    val results: List<com.batueksi.moviesapp.data.models.searchmodel.Search> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)