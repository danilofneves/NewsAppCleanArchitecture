package com.danilo.newsapp.domain.usecases

import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.domain.model.Article

interface FetchNews {
    suspend operator fun invoke(): Resource<List<Article>>
}