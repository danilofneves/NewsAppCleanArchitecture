package com.danilo.newsapp.domain.repository

import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.domain.model.Article


interface NewsRepository {
    suspend fun fetchNews(): Resource<List<Article>>
}