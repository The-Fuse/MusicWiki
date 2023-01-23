package com.rohit.musicwiki.models.tracks

import com.squareup.moshi.Json

data class Image(
    @Json(name = "#text")
    val text: String,
    val size: String
)