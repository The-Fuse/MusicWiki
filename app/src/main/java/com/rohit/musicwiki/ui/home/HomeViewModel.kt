package com.rohit.musicwiki.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.musicwiki.models.TopTagsResponse
import com.rohit.musicwiki.repository.MusicWikiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.rohit.musicwiki.utils.Result

class HomeViewModel @Inject constructor(private val musicWikiRepository: MusicWikiRepository): ViewModel() {
    private val _tags=  MutableLiveData<Result<TopTagsResponse>>()
    val tags: LiveData<Result<TopTagsResponse>>
        get() = _tags



    fun fetchTags() {
        viewModelScope.launch {
            _tags.value = musicWikiRepository.getTopTags()
        }
    }
}