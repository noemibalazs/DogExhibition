package com.noemi.android.dogexhibition.di

import com.noemi.android.dogexhibition.api.dataSource.DogAPI
import com.noemi.android.dogexhibition.api.dataSource.DogImageAPI
import com.noemi.android.dogexhibition.api.remoteDataSource.DogBreedRepository
import com.noemi.android.dogexhibition.api.remoteDataSource.DogBreedService
import com.noemi.android.dogexhibition.api.remoteDataSource.DogImageRepository
import com.noemi.android.dogexhibition.api.remoteDataSource.DogImageService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDogRepository(dogAPI: DogAPI): DogBreedService = DogBreedRepository(dogAPI)

    @Provides
    @Singleton
    fun provideDogImageRepository(dogImageAPI: DogImageAPI): DogImageService =
        DogImageRepository(dogImageAPI)
}