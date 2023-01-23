package com.rohit.musicwiki.network

import com.rohit.musicwiki.models.TagInfoResponse
import com.rohit.musicwiki.models.TopTagsResponse
import com.rohit.musicwiki.models.albumInfo.AlbumInfoResponse
import com.rohit.musicwiki.models.albums.TopAlbumsResponse
import com.rohit.musicwiki.models.artistInfo.ArtistInfoResponse
import com.rohit.musicwiki.models.artists.TopArtistsResponse
import com.rohit.musicwiki.models.topAlbumsByArtist.TopAlbumsByArtist
import com.rohit.musicwiki.models.topTrackByArtist.TopTracksByArtistResponse
import com.rohit.musicwiki.models.tracks.TopTracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/2.0/?method=tag.getTopTags&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTopTags(): Response<TopTagsResponse>

    @GET("/2.0/?method=tag.getinfo&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTagInfo(@Query("tag") tag: String): Response<TagInfoResponse>

    @GET("/2.0/?method=tag.gettopalbums&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTopAlbums(@Query("tag") tag: String): Response<TopAlbumsResponse>

    @GET("/2.0/?method=tag.gettopartists&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTopArtists(@Query("tag") tag: String): Response<TopArtistsResponse>

    @GET("/2.0/?method=tag.gettoptracks&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTopTracks(@Query("tag") tag: String): Response<TopTracksResponse>

    @GET("/2.0/?method=album.getinfo&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getAlbumInfo(@Query("artist") artist: String, @Query("album") album:String): Response<AlbumInfoResponse>

    @GET("/2.0/?method=artist.getinfo&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getArtistInfo(@Query("artist") artist: String): Response<ArtistInfoResponse>

    @GET("/2.0/?method=artist.gettoptracks&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTopTrackByArtist(@Query("artist") artist: String): Response<TopTracksByArtistResponse>

    @GET("/2.0/?method=artist.gettopalbums&api_key=ea7f9f45271be9646bc7be2ffcd8563d&format=json")
    suspend fun getTopAlbumsByArtist(@Query("artist") artist: String): Response<TopAlbumsByArtist>

}