package com.example.hackernews.modelview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackernews.data.repository.HNRepository
import com.example.hackernews.data.model.ItemStory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import com.example.hackernews.data.errorHandle.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class HNViewModel(private val hnRepository: HNRepository): ViewModel() {

    private val _topStoryDetails = MutableStateFlow<List<ItemStory>>(emptyList())
    val topStoryDetails = _topStoryDetails.asStateFlow()

    // Change the type of the channel to String to allow sending error messages.
    private val _showErrorToastChannel = Channel<String>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()


    fun fetchTopStories(isNetworkAvailable: Boolean) {
        if (!isNetworkAvailable) {
            viewModelScope.launch {
                _showErrorToastChannel.send("No internet connection available.")
            }
            return
        }
        viewModelScope.launch {
            when (val result = hnRepository.getTopStories().first()) {
                is Result.Success -> {
                    // Limit to fetch details for the first 30 top story IDs
                    val limitedStoryIds = result.data?.take(30)

                    val storyDetailsDeferred = limitedStoryIds?.map { id ->
                        async {
                            hnRepository.getStoryDetails(id).first()
                        }
                    }
                    // Await all concurrent operations
                    val storyDetailsResults = storyDetailsDeferred?.awaitAll()

                    // Filter for successful fetches and extract the data
                    val stories = storyDetailsResults?.mapNotNull { it as? Result.Success }?.map { it.data }
                    _topStoryDetails.emit(stories as List<ItemStory>)
                }
                is Result.Error -> {
                    _showErrorToastChannel.send("Failed to fetch top stories: ${result.message}")
                }
            }
        }
    }
}