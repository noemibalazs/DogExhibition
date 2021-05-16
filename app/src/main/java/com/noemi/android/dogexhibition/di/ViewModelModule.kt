package com.noemi.android.dogexhibition.di

import com.noemi.android.dogexhibition.api.remoteDataSource.DogBreedService
import com.noemi.android.dogexhibition.api.remoteDataSource.DogImageService
import com.noemi.android.dogexhibition.viewModel.DogViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideDogViewModel(dogBreedService: DogBreedService, dogImageService: DogImageService) =
        DogViewModel.Factory(dogBreedService, dogImageService)
}