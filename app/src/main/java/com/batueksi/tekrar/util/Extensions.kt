package com.batueksi.tekrar.util

import com.batueksi.tekrar.models.Content
import com.batueksi.tekrar.models.ContentList
import com.batueksi.tekrar.models.Result
import com.batueksi.tekrar.models.ResultXX

fun Result.toContent(): Content {
    return Content(
        id = (id ?: "") as Int,
        title = title ?: "",
        imagePath = backdrop_path ?: "",
        vote_avarage = (vote_average ?: "") as Double
    )
}

fun ResultXX.toContent(): Content {
    return Content(
        id = (id ?: "") as Int,
        title = name ?: "",
        imagePath = backdrop_path ?: "",
        vote_avarage = (vote_average ?: "") as Double
    )
}

fun List<Result>.toContentList(title: String): ContentList {
    return ContentList(
        title = title,
        contents = map { it.toContent() }
    )
}

fun List<ResultXX>.toContentList1(title: String): ContentList {
    return ContentList(
        title = title,
        contents = map { it.toContent() }
    )
}