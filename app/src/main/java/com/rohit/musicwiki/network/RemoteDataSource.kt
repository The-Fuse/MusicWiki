package com.rohit.musicwiki.network

import com.rohit.musicwiki.models.TagInfoResponse
import com.rohit.musicwiki.models.TopTagsResponse
import com.rohit.musicwiki.models.albumInfo.AlbumInfoResponse
import com.rohit.musicwiki.models.albums.TopAlbumsResponse
import com.rohit.musicwiki.models.artistInfo.ArtistInfoResponse
import com.rohit.musicwiki.models.artists.TopArtistsResponse
import com.rohit.musicwiki.models.tracks.TopTracksResponse
import com.rohit.musicwiki.utils.Result
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(), IRemoteDataSource {

    override suspend fun getTopTags(): Result<TopTagsResponse> =
        getResult { apiService.getTopTags() }

    override suspend fun getTagInfo(tag: String): Result<TagInfoResponse> =
        getResult { apiService.getTagInfo(tag) }

    override suspend fun getTopAlbums(tag: String): Result<TopAlbumsResponse> =
        getResult { apiService.getTopAlbums(tag) }

    override suspend fun getTopArtists(tag: String): Result<TopArtistsResponse> =
        getResult { apiService.getTopArtists(tag) }

    override suspend fun getTopTracks(tag: String): Result<TopTracksResponse> =
        getResult { apiService.getTopTracks(tag) }

    override suspend fun getAlbumInfoResponse(
        artist: String,
        album: String
    ): Result<AlbumInfoResponse> =
        getResult { apiService.getAlbumInfo(artist,album) }

    override suspend fun getArtistInfoResponse(artist: String): Result<ArtistInfoResponse> =
        getResult { apiService.getArtistInfo(artist) }
}

interface IRemoteDataSource{

    suspend fun getTopTags():Result<TopTagsResponse>
    suspend fun getTagInfo(tag: String): Result<TagInfoResponse>
    suspend fun getTopAlbums(tag: String): Result<TopAlbumsResponse>
    suspend fun getTopArtists(tag: String): Result<TopArtistsResponse>
    suspend fun getTopTracks(tag: String): Result<TopTracksResponse>
    suspend fun getAlbumInfoResponse(artist: String, album: String): Result<AlbumInfoResponse>
    suspend fun getArtistInfoResponse(artist: String): Result<ArtistInfoResponse>
}