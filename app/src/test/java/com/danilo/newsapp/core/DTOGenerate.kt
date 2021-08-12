package com.danilo.newsapp.core

import com.danilo.newsapp.data.response.NewsResponse
import com.danilo.newsapp.domain.model.Article
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class DTOGenerate {
    companion object {
        private const val NEWSURI = "NewsResponse.json"
    }
    private var newsResponse: NewsResponse = NewsResponse()

    init {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<NewsResponse> = moshi.adapter(NewsResponse::class.java)
        val jsonString = getStringJson(NEWSURI)
        adapter.fromJson(jsonString)?.let {
            newsResponse = it
        }
    }

    fun generateNewsModel(): List<Article> {
        return newsResponse.articles.map {  it.toData() }
    }

    fun generateNewsModelEmpty(): List<Article> {
        return listOf()
    }
}