package com.example.hackernews.data.repository

import com.example.hackernews.data.errorHandle.Result
import com.example.hackernews.data.api.Api
import com.example.hackernews.data.model.ItemStory
import com.example.hackernews.data.model.TopStories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class HNRepositoryImpl(private val api: Api) : HNRepository {
    override fun getTopStories(): Flow<Result<TopStories>> = flow {
        try {
            val topStories = api.getTopStories()
            emit(Result.Success(topStories))
        } catch (e: Exception) {
            emit(Result.Error(message = "Failed to fetch top stories : ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getStoryDetails(storyId: Int): Flow<Result<ItemStory>> = flow {
        try {
            val storyDetails = api.getItemStory(storyId)
            emit(Result.Success(storyDetails))
        } catch (e: Exception) {
            emit(Result.Error(message = "Failed to fetch story details: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)
}