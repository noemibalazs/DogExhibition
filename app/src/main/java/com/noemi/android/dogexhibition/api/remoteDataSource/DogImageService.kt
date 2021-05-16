package com.noemi.android.dogexhibition.api.remoteDataSource

import com.noemi.android.dogexhibition.api.model.RandomImageResult
import io.reactivex.Single

interface DogImageService {

    fun getSimilarDogImages(name: String): Single<RandomImageResult>
}