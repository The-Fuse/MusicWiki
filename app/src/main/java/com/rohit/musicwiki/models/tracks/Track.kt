package com.rohit.musicwiki.models.tracks

data class Track(
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)