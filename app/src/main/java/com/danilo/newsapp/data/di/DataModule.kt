package com.danilo.newsapp.data.di

import com.danilo.newsapp.data.remote.apidatasource.NewsApiDataSource
import com.danilo.newsapp.data.remote.apidatasource.retrofit.NewsApiDataSourceRetro
import com.danilo.newsapp.data.remote.service.NewsService
import com.danilo.newsapp.data.repository.NewsRepositoryImpl
import com.danilo.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    private val BASE_URL = "https://newsapi.org/v2/"
    private val timeoutConnect = 3   //In seconds
    private val timeoutRead = 3   //In seconds

    @Provides
    fun provideOkHttpBuilder(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
                .readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideServiceNewsService(retrofit: Retrofit ) : NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun bindNewsApiDataSource(
            newsService: NewsService
    ): NewsApiDataSource = NewsApiDataSourceRetro(newsService)

    @Singleton
    @Provides
    fun bindNewsRepository(newsApiDataSource: NewsApiDataSource): NewsRepository = NewsRepositoryImpl(newsApiDataSource)


}