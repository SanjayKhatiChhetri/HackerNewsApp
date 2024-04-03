package com.example.hackernews.data.repository

import com.example.hackernews.data.errorHandle.Result
import com.example.hackernews.data.model.ItemStory
import com.example.hackernews.data.model.TopStories
import kotlinx.coroutines.flow.Flow


interface HNRepository {
    fun getTopStories(): Flow<Result<TopStories>>
    fun getStoryDetails(storyId: Int): Flow<Result<ItemStory>>
}