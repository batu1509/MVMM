package com.batueksi.tekrar.util

import android.content.Context
import android.widget.Toast
import com.batueksi.tekrar.R
import com.batueksi.tekrar.data.models.Content
import com.batueksi.tekrar.data.models.ContentList
import com.batueksi.tekrar.data.models.Result
import com.batueksi.tekrar.data.models.ResultXX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Result.toContent(): Content {
    return Content(
        id = (id ?: "") as Int,
        title = title ?: "",
        imagePath = backdrop_path ?: "",
        vote_count = (vote_count ?: "") as Int,
        vote_avarage = (vote_average ?: "") as Double
    )
}

fun ResultXX.toContent(): Content {
    return Content(
        id = (id ?: "") as Int,
        title = name ?: "",
        imagePath = backdrop_path ?: "",
        vote_count = (vote_count ?: "") as Int,
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

suspend fun showToast(context: Context) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
    }
}