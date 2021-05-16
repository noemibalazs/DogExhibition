package com.noemi.android.dogexhibition

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.noemi.android.dogexhibition.api.remoteDataSource.DogImageService
import com.noemi.android.dogexhibition.viewModel.DogViewModel
import org.junit.Before
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.noemi.android.dogexhibition.api.model.Breed
import com.noemi.android.dogexhibition.api.model.BreedImages
import com.noemi.android.dogexhibition.api.model.DogBreedResult
import com.noemi.android.dogexhibition.api.model.RandomImageResult
import com.noemi.android.dogexhibition.api.remoteDataSource.DogBreedService
import io.reactivex.Single
import org.junit.Test
import java.lang.NullPointerException

class ViewModelUnitTest {

    private lateinit var dogViewModel: DogViewModel
    private val dogImageService: DogImageService = mock()
    private val dogBreedService: DogBreedService = mock()
    private lateinit var failure: Throwable
    private val breedObserver: Observer<MutableList<Breed>> = mock()
    private val randomImageObserver: Observer<MutableList<BreedImages>> = mock()
    private val progressObserver: Observer<Boolean> = mock()
    private val errorObserver: Observer<String> = mock()

    @Before
    fun setUp() {
        dogViewModel = DogViewModel(dogBreedService, dogImageService)
        failure = Throwable("An error has occurred, try it again!")
    }

    @Test(expected = NullPointerException::class)
    fun testGetDogBreedsList_Success() {
        val response = mock<DogBreedResult>()
        whenever(dogBreedService.getDogBreedList()).thenReturn(Single.just(response))

        dogViewModel.getDogBreeds()
        dogViewModel.dogBreeds.observeForever(breedObserver)

        val breeds = mutableListOf<Breed>()
        response.message.forEachIndexed { index, name ->
            breeds.add(Breed(id = index, breedName = name))
        }

        verify(progressObserver).onChanged(true)
        verify(breedObserver).onChanged(breeds)
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testGetDogBreedsList_Error() {
        whenever(dogBreedService.getDogBreedList()).thenReturn(Single.error(failure))

        dogViewModel.getDogBreeds()
        dogViewModel.dogBreeds.observeForever(breedObserver)

        val captor = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(breedObserver).onChanged(null)
        verify(progressObserver).onChanged(false)
        verify(errorObserver).onChanged(captor.capture())
    }

    @Test(expected = NullPointerException::class)
    fun testGetRandomImageList_Success() {
        val response = mock<RandomImageResult>()
        val name = "vizsla"
        whenever(dogImageService.getSimilarDogImages(name)).thenReturn(Single.just(response))

        dogViewModel.getRandomImages(name)
        dogViewModel.randomDogImages.observeForever(randomImageObserver)

        val randomImages = mutableListOf<BreedImages>()
        response.message?.forEachIndexed { index, url ->
            randomImages.add(BreedImages(id = index, url = url))
        }

        verify(progressObserver).onChanged(true)
        verify(randomImageObserver).onChanged(randomImages)
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testGetRandomImageList_Error() {
        val name = "vizsla"
        whenever(dogImageService.getSimilarDogImages(name)).thenReturn(Single.error(failure))

        dogViewModel.getRandomImages(name)
        dogViewModel.randomDogImages.observeForever(randomImageObserver)

        val captor = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(randomImageObserver).onChanged(null)
        verify(progressObserver).onChanged(false)
        verify(errorObserver).onChanged(captor.capture())
    }
}