package com.noemi.android.dogexhibition.api.remoteDataSource

import com.noemi.android.dogexhibition.api.dataSource.DogAPI
import com.noemi.android.dogexhibition.api.model.DogBreedResult
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogBreedRepository @Inject constructor(private val dogAPI: DogAPI) : DogBreedService {

    override fun getDogBreedList(): Single<DogBreedResult> {
        return dogAPI.getDogBreedList()
    }
}