package com.example.mvvm_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_news_app.models.NewsResponse
import com.example.mvvm_news_app.repository.NewsRepository
import com.example.mvvm_news_app.util.Resource
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository : NewsRepository) : ViewModel() {

val breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    fun getBreakingNews(countryCode :String) = viewModelScope.launch {
         breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getbreakingNews(countryCode,breakingNewsPage)
    }


}