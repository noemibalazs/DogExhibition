package com.noemi.android.dogexhibition.api.dataSource

import com.noemi.android.dogexhibition.api.model.RandomImageResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogImageAPI {

    @GET("{name}/images/random/10")
    fun getSimilarDogImages(@Path("name") name: String): Single<RandomImageResult>
}