package com.danilo.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.danilo.newsapp.core.ViewModelFactory
import com.danilo.newsapp.core.extensions.observe
import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.data.exception.ApiError
import com.danilo.newsapp.databinding.ActivityMainBinding
import com.danilo.newsapp.domain.model.Article
import com.danilo.newsapp.presentation.adapter.NewsAdapter
import com.danilo.newsapp.presentation.extensions.*
import dagger.android.AndroidInjection
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var newsListViewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.ltvArticles.layoutManager = layoutManager
        binding.ltvArticles.setHasFixedSize(true)

        newsListViewModel = viewModelFactory.create(NewsViewModel::class.java)
        newsListViewModel.getNews()

        observe(newsListViewModel.newsLiveData, ::handleNewsList)
        observe(newsListViewModel.error, ::handleError)
    }

    private fun bindListData(articles: List<Article>) {
        if (!(articles.isNullOrEmpty())) {
            val newsAdapter = NewsAdapter(articles)
            binding.ltvArticles.adapter = newsAdapter
            binding.vfArticles.toData()
        }
        else{
            binding.vfArticles.toEmpty()
        }
    }

    private fun handleNewsList(articles: Resource<List<Article>>) {
        when (articles) {
            is Resource.Loading -> {binding.vfArticles.toLoading()}
            is Resource.Success -> articles.data?.let {bindListData(it)}
            is Resource.DataError -> {
                binding.vfArticles.toError()
                newsListViewModel.notifyFailure(articles.error?.apiError)
            }
        }
    }

    private fun handleError(apiError: ApiError) {
        binding.txtError.text = apiError.getMessage()
        toast(apiError.getMessage())
    }
}