package com.rohit.musicwiki.models.albums

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)