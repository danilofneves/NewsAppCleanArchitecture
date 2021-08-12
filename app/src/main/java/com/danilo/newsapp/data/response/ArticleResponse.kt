package com.danilo.newsapp.data.response

import com.danilo.newsapp.domain.model.Article
import com.squareup.moshi.Json

data class ArticleResponse(
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "content") val content: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "publishedAt") val publishedAt: String?,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "urlToImage") val urlToImage: String?
){
    fun toData(): Article {
        return Article(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            title = title,
            urlToImage = urlToImage
        )
    }
}