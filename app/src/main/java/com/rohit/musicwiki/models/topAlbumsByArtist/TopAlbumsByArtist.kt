package com.rohit.musicwiki.models.topAlbumsByArtist

import com.rohit.musicwiki.models.albums.Album

data class TopAlbumsByArtist(
    val topalbums: Topalbums
)

data class Topalbums(
    val album: List<Album>
)