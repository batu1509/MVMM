package com.batueksi.moviesapp.data.models.detailsmodel

data class MovieDetailsModel(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any? = null,
    val budget: Int = 0,
    val genres: List<com.batueksi.moviesapp.data.models.detailsmodel.Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<com.batueksi.moviesapp.data.models.detailsmodel.ProductionCompany> = listOf(),
    val production_countries: List<com.batueksi.moviesapp.data.models.detailsmodel.ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<com.batueksi.moviesapp.data.models.detailsmodel.SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)