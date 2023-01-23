package com.rohit.musicwiki.ui.artistDetail

import androidx.lifecycle.*
import com.rohit.musicwiki.models.TagInfoResponse
import com.rohit.musicwiki.models.albumInfo.AlbumInfoResponse
import com.rohit.musicwiki.models.artistInfo.ArtistInfoResponse
import com.rohit.musicwiki.models.topAlbumsByArtist.TopAlbumsByArtist
import com.rohit.musicwiki.models.topTrackByArtist.TopTracksByArtistResponse
import com.rohit.musicwiki.repository.MusicWikiRepository
import com.rohit.musicwiki.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtistDetailsViewModel @Inject constructor(private val musicWikiRepository: MusicWikiRepository): ViewModel() {
    private val _artistInfo=  MutableLiveData<Result<ArtistInfoResponse>>()
    val artistInfo: LiveData<Result<ArtistInfoResponse>>
        get() = _artistInfo

    private val _artistTopTrack=  MutableLiveData<Result<TopTracksByArtistResponse>>()
    val artistTopTrack: LiveData<Result<TopTracksByArtistResponse>>
        get() = _artistTopTrack

    private val _artistTopAlbum=  MutableLiveData<Result<TopAlbumsByArtist>>()
    val artistTopAlbum: LiveData<Result<TopAlbumsByArtist>>
        get() = _artistTopAlbum


    fun fetchArtistInfo(artist: String) {
        viewModelScope.launch {
            _artistInfo.value = musicWikiRepository.getArtistInfo(artist)
        }
    }

    fun fetchTopTrackByArtist(artist: String) {
        viewModelScope.launch {
            _artistTopTrack.value = musicWikiRepository.getTopTrackByArtist(artist)
        }
    }

    fun fetchTopAlbumByArtist(artist: String) {
        viewModelScope.launch {
            _artistTopAlbum.value = musicWikiRepository.getTopAlbumByArtist(artist)
        }
    }
}

class ArtistDetailViewModelFactory @Inject constructor( private val musicWikiRepository: MusicWikiRepository ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistDetailsViewModel(musicWikiRepository) as T
    }

}