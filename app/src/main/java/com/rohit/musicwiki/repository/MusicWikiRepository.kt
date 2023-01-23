package com.rohit.musicwiki.repository

import com.rohit.musicwiki.network.IRemoteDataSource
import javax.inject.Inject

class MusicWikiRepository @Inject constructor(private val remoteDataSource: IRemoteDataSource) {

    suspend fun getTopTags() =
        remoteDataSource.getTopTags()

    suspend fun getTagInfo(tag: String) =
        remoteDataSource.getTagInfo(tag)

    suspend fun getTopArtists(tag: String) =
        remoteDataSource.getTopArtists(tag)

    suspend fun getTopAlbums(tag: String) =
        remoteDataSource.getTopAlbums(tag)

    suspend fun getTopTracks(tag: String) =
        remoteDataSource.getTopTracks(tag)

    suspend fun getAlbumInfo(artist: String, album: String) =
        remoteDataSource.getAlbumInfoResponse(artist,album)

    suspend fun getArtistInfo(artist: String) =
        remoteDataSource.getArtistInfoResponse(artist)

    suspend fun getTopTrackByArtist(artist: String)=
        remoteDataSource.getTopTrackByArtist(artist)

    suspend fun getTopAlbumByArtist(artist: String) =
        remoteDataSource.getTopAlbumsByArtist(artist)
}