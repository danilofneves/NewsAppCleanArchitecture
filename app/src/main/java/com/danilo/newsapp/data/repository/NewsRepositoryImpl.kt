package com.danilo.newsapp.data.repository

import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.data.remote.apidatasource.NewsApiDataSource
import com.danilo.newsapp.domain.model.Article
import com.danilo.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (private val newsApiDataSource:NewsApiDataSource):
    NewsRepository {
    override suspend fun fetchNews(): Resource<List<Article>> {
        return newsApiDataSource.fetchNews()
    }
}