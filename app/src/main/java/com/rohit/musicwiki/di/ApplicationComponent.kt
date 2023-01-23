package com.rohit.musicwiki.di

import android.content.Context
import com.rohit.musicwiki.ui.albumDetail.AlbumDetailFragment
import com.rohit.musicwiki.ui.artistDetail.ArtistDetailFragment
import com.rohit.musicwiki.ui.genreDetail.GenreDetailFragment
import com.rohit.musicwiki.ui.genreDetail.TabFragment
import com.rohit.musicwiki.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, InterfaceModule::class])
interface ApplicationComponent {

    fun injectHome(fragment: HomeFragment)

    fun injectGenreDetail(fragment: GenreDetailFragment)

    fun injectTab(fragment: TabFragment)

    fun injectAlbumDetail(fragment: AlbumDetailFragment)

    fun injectArtistDetail(fragment: ArtistDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}