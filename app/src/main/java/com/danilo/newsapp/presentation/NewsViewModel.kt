package com.danilo.newsapp.presentation

import androidx.lifecycle.*
import com.danilo.newsapp.data.Resource
import com.danilo.newsapp.data.exception.ApiError
import com.danilo.newsapp.domain.model.Article
import com.danilo.newsapp.domain.usecases.FetchNews
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val fetchNews: FetchNews
):ViewModel(){

    private val _newsLiveData: MutableLiveData<Resource<List<Article>>> = MutableLiveData()
    val newsLiveData: LiveData<Resource<List<Article>>> get() = _newsLiveData

    val error = MutableLiveData<ApiError>()

    fun getNews(){
        viewModelScope.launch {
            _newsLiveData.value = Resource.Loading()
            _newsLiveData.value = fetchNews()
        }
    }

    fun notifyFailure(apiError: ApiError?) {
        error.postValue(apiError!!)
    }


}