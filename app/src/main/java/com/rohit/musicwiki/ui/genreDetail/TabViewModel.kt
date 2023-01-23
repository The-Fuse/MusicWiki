package com.rohit.musicwiki.ui.genreDetail

import androidx.lifecycle.*
import com.rohit.musicwiki.models.TabItem
import com.rohit.musicwiki.models.albums.TopAlbumsResponse
import com.rohit.musicwiki.models.artists.TopArtistsResponse
import com.rohit.musicwiki.models.tracks.TopTracksResponse
import com.rohit.musicwiki.repository.MusicWikiRepository
import com.rohit.musicwiki.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class TabViewModel @Inject constructor(private val musicWikiRepository: MusicWikiRepository): ViewModel() {
    private val _artistData=  MutableLiveData<Result<TopArtistsResponse>>()
    val artistData: LiveData<Result<TopArtistsResponse>>
        get() = _artistData

    private val _albumsData=  MutableLiveData<Result<TopAlbumsResponse>>()
    val albumsData: LiveData<Result<TopAlbumsResponse>>
        get() = _albumsData

    private val _tracksData=  MutableLiveData<Result<TopTracksResponse>>()
    val tracksData: LiveData<Result<TopTracksResponse>>
        get() = _tracksData


    fun fetchTopArtists(tag: String) {
        val data = mutableListOf<TabItem>()
        viewModelScope.launch {
            _artistData.value   = musicWikiRepository.getTopArtists(tag)
        }
    }

    fun fetchTopAlbums(tag: String) {
        val data = mutableListOf<TabItem>()
        viewModelScope.launch {
            _albumsData.value   = musicWikiRepository.getTopAlbums(tag)
        }
    }

    fun fetchTopTracks(tag: String) {
        val data = mutableListOf<TabItem>()
        viewModelScope.launch {
            _tracksData.value   = musicWikiRepository.getTopTracks(tag)
        }
    }
}

class TabViewModelFactory @Inject constructor( private val musicWikiRepository: MusicWikiRepository ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TabViewModel(musicWikiRepository) as T
    }

}