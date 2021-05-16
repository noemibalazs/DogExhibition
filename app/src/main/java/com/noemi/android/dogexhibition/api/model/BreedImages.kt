package com.noemi.android.dogexhibition.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedImages(
    val id: Int,
    val url: String
) : Parcelable {
    override fun toString(): String {
        return "BreedImages: id=$id, url='$url'"
    }
}
