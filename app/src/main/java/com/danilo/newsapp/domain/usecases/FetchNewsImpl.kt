package com.danilo.newsapp.domain.usecases

import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.domain.model.Article
import com.danilo.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class FetchNewsImpl  @Inject constructor(
    private val repository: NewsRepository
) : FetchNews {
    override suspend operator fun invoke(): Resource<List<Article>> = repository.fetchNews()
}