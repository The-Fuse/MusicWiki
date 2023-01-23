package com.rohit.musicwiki.models.artists

import com.squareup.moshi.Json

data class Image(
    @Json(name = "#text")
    val text: String,
    val size: String
)