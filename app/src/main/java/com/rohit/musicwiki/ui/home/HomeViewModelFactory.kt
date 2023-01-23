package com.rohit.musicwiki.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohit.musicwiki.repository.MusicWikiRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor( private val musicWikiRepository: MusicWikiRepository ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(musicWikiRepository) as T
    }

}