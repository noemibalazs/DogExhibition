package com.noemi.android.dogexhibition.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
    val id: Int,
    val breedName: String
) : Parcelable {
    override fun toString(): String {
        return "Breed: breedName='$breedName' - id=$id"
    }
}