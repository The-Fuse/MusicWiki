package com.rohit.musicwiki.ui.genreDetail

import androidx.lifecycle.*
import com.rohit.musicwiki.models.TagInfoResponse
import com.rohit.musicwiki.repository.MusicWikiRepository
import com.rohit.musicwiki.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreDetailsViewModel @Inject constructor(private val musicWikiRepository: MusicWikiRepository): ViewModel() {
    private val _tagInfo=  MutableLiveData<Result<TagInfoResponse>>()
    val tagInfo: LiveData<Result<TagInfoResponse>>
        get() = _tagInfo



    fun fetchTags(tag: String) {
        viewModelScope.launch {
            _tagInfo.value = musicWikiRepository.getTagInfo(tag)
        }
    }
}

class GenreDetailViewModelFactory @Inject constructor( private val musicWikiRepository: MusicWikiRepository ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenreDetailsViewModel(musicWikiRepository) as T
    }

}