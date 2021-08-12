package com.danilo.newsapp.data.response

import com.squareup.moshi.Json

data class NewsResponse(
    @field:Json(name = "articles") val articles: List<ArticleResponse> = listOf(),
    @field:Json(name = "status") val status: String = "",
    @field:Json(name = "totalResults") val totalResults: Int = 0
)