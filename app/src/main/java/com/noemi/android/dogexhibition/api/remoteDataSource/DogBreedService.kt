package com.noemi.android.dogexhibition.api.remoteDataSource

import com.noemi.android.dogexhibition.api.model.DogBreedResult
import io.reactivex.Single

interface DogBreedService {

    fun getDogBreedList(): Single<DogBreedResult>
}