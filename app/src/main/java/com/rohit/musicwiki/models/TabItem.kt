package com.rohit.musicwiki.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TabItem(
    val title: String?,
    val image: String?,
    val artistName: String
): Parcelable
