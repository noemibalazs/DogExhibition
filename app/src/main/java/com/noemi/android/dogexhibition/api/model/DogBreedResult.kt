package com.noemi.android.dogexhibition.api.model

import com.google.gson.annotations.SerializedName

data class DogBreedResult(
    @SerializedName("message") val message: MutableList<String>
) {
    override fun toString(): String {
        return "DogBreedResult: message=$message"
    }
}