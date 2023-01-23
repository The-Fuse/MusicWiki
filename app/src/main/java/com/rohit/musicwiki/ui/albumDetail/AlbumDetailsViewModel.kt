package com.rohit.musicwiki.ui.albumDetail

import androidx.lifecycle.*
import com.rohit.musicwiki.models.TagInfoResponse
import com.rohit.musicwiki.models.albumInfo.AlbumInfoResponse
import com.rohit.musicwiki.repository.MusicWikiRepository
import com.rohit.musicwiki.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(private val musicWikiRepository: MusicWikiRepository): ViewModel() {
    private val _albumInfo=  MutableLiveData<Result<AlbumInfoResponse>>()
    val albumInfo: LiveData<Result<AlbumInfoResponse>>
        get() = _albumInfo



    fun fetchAlbumInfo(artist: String, album: String) {
        viewModelScope.launch {
            _albumInfo.value = musicWikiRepository.getAlbumInfo(artist,album)
        }
    }
}

class AlbumDetailViewModelFactory @Inject constructor( private val musicWikiRepository: MusicWikiRepository ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(musicWikiRepository) as T
    }

}