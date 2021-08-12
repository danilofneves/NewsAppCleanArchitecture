package com.danilo.newsapp.domain.model

data class Article(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: String?,
    var title: String,
    var urlToImage: String?
)