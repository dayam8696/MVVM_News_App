package com.example.mvvm_news_app.repository

import com.example.mvvm_news_app.api.RetrofitInstance
import com.example.mvvm_news_app.db.ArticleDatabase
import com.example.mvvm_news_app.models.Article

class NewsRepository(
    val db :ArticleDatabase
) {

    suspend fun getbreakingNews( countryCode :String ,pageNumber :Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
    suspend fun searchNews (searchQuery:String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert (article:Article) = db.getArticle().upsert(article)
    fun getSavedNews() =db.getArticle().getAllArticles()

    suspend fun deleteArticles (article: Article) = db.getArticle().deleteArticle(article)
}