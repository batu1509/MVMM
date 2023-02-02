package com.batueksi.tekrar.data.models.searchmodel

data class SearchModel(
    val page: Int = 0,
    val results: List<com.batueksi.tekrar.data.models.searchmodel.Result> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)