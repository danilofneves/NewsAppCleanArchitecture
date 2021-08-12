package com.danilo.newsapp.domain.di

import com.danilo.newsapp.domain.repository.NewsRepository
import com.danilo.newsapp.domain.usecases.FetchNews
import com.danilo.newsapp.domain.usecases.FetchNewsImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFetchNews(repository: NewsRepository): FetchNews = FetchNewsImpl(repository)

}