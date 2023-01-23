package com.rohit.musicwiki.models.artistInfo

import com.rohit.musicwiki.models.Tag

data class ArtistInfoResponse(
    val artist: Artist
)

data class Artist(
    val bio: Bio,
    val name: String,
    val stats: Stats,
    val tags: Tags,
)

data class Bio(
    val content: String,
    val published: String,
    val summary: String
)

data class Stats(
    val listeners: String,
    val playcount: String
)

data class Tags(
    val tag: List<Tag>
)