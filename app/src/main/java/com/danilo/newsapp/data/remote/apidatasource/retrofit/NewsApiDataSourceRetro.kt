package com.danilo.newsapp.data.remote.apidatasource.retrofit


import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.data.exception.ResponseError
import com.danilo.newsapp.data.remote.apidatasource.NewsApiDataSource
import com.danilo.newsapp.data.remote.helper.safeCall
import com.danilo.newsapp.data.remote.service.NewsService
import com.danilo.newsapp.data.response.NewsResponse
import com.danilo.newsapp.domain.model.Article
import javax.inject.Inject

class NewsApiDataSourceRetro @Inject constructor(
    private val newsService: NewsService
) : NewsApiDataSource {

    override suspend fun fetchNews(): Resource<List<Article>>{
        return when (val response = safeCall {newsService.getNews()}) {
            is NewsResponse -> {
                Resource.Success(data = response.articles.map {
                    it.toData()
                })
            }
            else -> {
                Resource.DataError(response as ResponseError)
            }
        }
    }

}