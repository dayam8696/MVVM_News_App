package com.example.mvvm_news_app.api

import com.example.mvvm_news_app.models.NewsResponse
import com.example.mvvm_news_app.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String = "in",
        @Query("pages")
        pageNumber:Int = 1,
        @Query("apiKey")
        apiKey:String =API_KEY

    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery:String ,
        @Query("pages")
        pageNumber:Int = 1,
        @Query("apiKey")
        apiKey:String =API_KEY

    ):Response<NewsResponse>
}