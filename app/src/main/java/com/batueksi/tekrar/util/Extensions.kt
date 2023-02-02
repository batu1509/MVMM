package com.batueksi.tekrar.util

import com.batueksi.tekrar.data.models.Content
import com.batueksi.tekrar.data.models.ContentList
import com.batueksi.tekrar.data.models.Result
import com.batueksi.tekrar.data.models.ResultXX

fun com.batueksi.tekrar.data.models.Result.toContent(): com.batueksi.tekrar.data.models.Content {
    return com.batueksi.tekrar.data.models.Content(
        id = (id ?: "") as Int,
        title = title ?: "",
        imagePath = backdrop_path ?: "",
        vote_avarage = (vote_average ?: "") as Double
    )
}

fun com.batueksi.tekrar.data.models.ResultXX.toContent(): com.batueksi.tekrar.data.models.Content {
    return com.batueksi.tekrar.data.models.Content(
        id = (id ?: "") as Int,
        title = name ?: "",
        imagePath = backdrop_path ?: "",
        vote_avarage = (vote_average ?: "") as Double
    )
}

fun List<com.batueksi.tekrar.data.models.Result>.toContentList(title: String): com.batueksi.tekrar.data.models.ContentList {
    return com.batueksi.tekrar.data.models.ContentList(
        title = title,
        contents = map { it.toContent() }
    )
}

fun List<com.batueksi.tekrar.data.models.ResultXX>.toContentList1(title: String): com.batueksi.tekrar.data.models.ContentList {
    return com.batueksi.tekrar.data.models.ContentList(
        title = title,
        contents = map { it.toContent() }
    )
}