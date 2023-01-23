package com.rohit.musicwiki.models.tracks

data class Track(
    val artist: Artist,
    val image: List<Image>,
    val name: String,
)