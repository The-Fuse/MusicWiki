package com.rohit.musicwiki.models.albumInfo

import com.rohit.musicwiki.models.Tag

data class AlbumInfoResponse(
    val album: Album
)

data class Album(
    val tags: Tags,
    val wiki: Wiki
)



data class Tags(
    val tag: List<Tag>
)

data class Wiki(
    val content: String,
    val published: String,
    val summary: String
)