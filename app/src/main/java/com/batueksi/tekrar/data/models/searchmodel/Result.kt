package com.batueksi.tekrar.data.models.searchmodel

data class Result(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    val gender: Int? = null,
    val genre_ids: List<Int>? = null,
    val id: Int = 0,
    val known_for: List<com.batueksi.tekrar.data.models.searchmodel.KnownFor>? = null,
    val known_for_department: String? = null,
    val media_type: String = "",
    val name: String? = null,
    val origin_country: List<String>? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val poster_path: String? = null,
    val profile_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)