package com.noemi.android.dogexhibition.api.dataSource

import com.noemi.android.dogexhibition.api.model.DogBreedResult
import io.reactivex.Single
import retrofit2.http.GET

interface DogAPI {

    @GET("list")
    fun getDogBreedList(): Single<DogBreedResult>
}