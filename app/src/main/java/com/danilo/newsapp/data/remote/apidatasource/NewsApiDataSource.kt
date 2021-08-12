package com.danilo.newsapp.data.remote.apidatasource

import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.domain.model.Article


interface NewsApiDataSource {

    suspend fun fetchNews(): Resource<List<Article>>
}