package com.batueksi.tekrar.util

import com.batueksi.tekrar.models.Content
import com.batueksi.tekrar.models.ContentList
import com.batueksi.tekrar.models.Result
import com.batueksi.tekrar.models.ResultXX

fun Result.toContent(): Content {
    return Content(
        title = title ?: "",
        imagePath = backdrop_path ?: ""
    )
}

fun ResultXX.toContent(): Content {
    return Content(
        title = name ?: "",
        imagePath = backdrop_path ?: ""
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