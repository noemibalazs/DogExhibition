package com.noemi.android.dogexhibition.api.model

import com.google.gson.annotations.SerializedName

data class RandomImageResult(
    @SerializedName("message") val message: MutableList<String>? = null
) {
    override fun toString(): String {
        return "RandomImageResult: message=$message"
    }
}