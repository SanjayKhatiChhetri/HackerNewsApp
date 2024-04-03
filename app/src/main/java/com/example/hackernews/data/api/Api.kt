package com.example.hackernews.data.api

import com.example.hackernews.data.model.ItemStory
import com.example.hackernews.data.model.TopStories

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("v0/topstories.json")
    suspend fun getTopStories(): TopStories

    @GET("v0/item/{itemId}.json")
    suspend fun getItemStory(@Path("itemId") itemId: Int): ItemStory

    companion object {
        const val BASE_URL = "https://hacker-news.firebaseio.com/"
    }
}