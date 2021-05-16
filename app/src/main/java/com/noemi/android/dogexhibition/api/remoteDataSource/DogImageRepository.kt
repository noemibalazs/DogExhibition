package com.noemi.android.dogexhibition.api.remoteDataSource

import com.noemi.android.dogexhibition.api.dataSource.DogImageAPI
import com.noemi.android.dogexhibition.api.model.RandomImageResult
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogImageRepository @Inject constructor(private val dogImageAPI: DogImageAPI) : DogImageService {

    override fun getSimilarDogImages(name: String): Single<RandomImageResult> {
        return dogImageAPI.getSimilarDogImages(name)
    }
}