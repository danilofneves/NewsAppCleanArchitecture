package com.danilo.newsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danilo.newsapp.core.DTOGenerate
import com.danilo.newsapp.core.MainCoroutineRule
import com.danilo.newsapp.core.getOrAwaitValue
import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.data.exception.IOErrorException
import com.danilo.newsapp.data.exception.ResponseError
import com.danilo.newsapp.domain.model.Article
import com.danilo.newsapp.domain.usecases.FetchNews
import com.danilo.newsapp.presentation.NewsViewModel
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var newsListViewModel: NewsViewModel

    private lateinit var fetchNews: FetchNews
    private val testModelsGenerator: DTOGenerate = DTOGenerate()

    @Before
    fun setup() {
        fetchNews = spyk()
        newsListViewModel =
            NewsViewModel(
                fetchNews
            )
    }

    @Test
    fun handleFetchNews() {
        val newsModeltest = testModelsGenerator.generateNewsModel()

        coEvery{ fetchNews()} returns Resource.Success(data = newsModeltest)

        newsListViewModel.getNews()
        newsListViewModel.newsLiveData.observeForever { }

        val isEmptyList = newsListViewModel.newsLiveData.getOrAwaitValue().data.isNullOrEmpty()
        assert(newsModeltest == newsListViewModel.newsLiveData.getOrAwaitValue().data)
        assert(!isEmptyList)
    }

    @Test
    fun handleFetchNewsEmpty() {
        val newsModeltest = testModelsGenerator.generateNewsModelEmpty()

        coEvery{ fetchNews()} returns Resource.Success(data = newsModeltest)

        newsListViewModel.getNews()
        newsListViewModel.newsLiveData.observeForever { }

        val isEmptyList = newsListViewModel.newsLiveData.getOrAwaitValue().data.isNullOrEmpty()
        assert(newsModeltest == newsListViewModel.newsLiveData.getOrAwaitValue().data)
        assert(isEmptyList)
    }

    @Test
    fun handleFetchNewsError() {
        val error: Resource<List<Article>> = Resource.DataError(ResponseError(IOErrorException()))

        coEvery{ fetchNews()} returns error

        newsListViewModel.getNews()
        newsListViewModel.newsLiveData.observeForever { }

        assert(newsListViewModel.newsLiveData.getOrAwaitValue().error?.apiError is IOErrorException)
    }


}