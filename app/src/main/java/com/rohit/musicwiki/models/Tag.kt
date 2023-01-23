package com.rohit.musicwiki.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    val name: String = "",
): Parcelable