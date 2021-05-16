package com.noemi.android.dogexhibition

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.noemi.android.dogexhibition.api.dataSource.DogAPI
import com.noemi.android.dogexhibition.api.dataSource.DogImageAPI
import com.noemi.android.dogexhibition.api.model.DogBreedResult
import com.noemi.android.dogexhibition.api.model.RandomImageResult
import com.noemi.android.dogexhibition.api.remoteDataSource.DogBreedRepository
import com.noemi.android.dogexhibition.api.remoteDataSource.DogImageRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RepositoryModuleUnitTest {

    private lateinit var dogBreedRepository: DogBreedRepository
    private lateinit var dogImageRepository: DogImageRepository
    private val dogApi: DogAPI = mock()
    private val dogImageAPI: DogImageAPI = mock()
    private lateinit var failure: Throwable

    @Before
    fun setUp() {
        dogBreedRepository = DogBreedRepository(dogApi)
        dogImageRepository = DogImageRepository(dogImageAPI)
        failure = Throwable("Try it again, an error has occurred.")
    }

    @Test
    fun testGetBreedList_Complete() {
        val response = mock<DogBreedResult>()
        whenever(dogBreedRepository.getDogBreedList()).thenReturn(Single.just(response))
        dogBreedRepository.getDogBreedList().test().assertComplete()
        verify(dogApi).getDogBreedList()
    }

    @Test
    fun testGetBreedList_Error() {
        whenever(dogBreedRepository.getDogBreedList()).thenReturn(Single.error(failure))
        dogBreedRepository.getDogBreedList().test().assertError(failure)
        verify(dogApi).getDogBreedList()
    }

    @Test
    fun testGetRandomImageList_Complete() {
        val response = mock<RandomImageResult>()
        val name = "vizsla"
        whenever(dogImageRepository.getSimilarDogImages(name)).thenReturn(Single.just(response))
        dogImageRepository.getSimilarDogImages(name).test().assertComplete()
        verify(dogImageAPI).getSimilarDogImages(name)
    }

    @Test
    fun testGetRandomImageList_Error() {
        val name = "vizsla"
        whenever(dogImageRepository.getSimilarDogImages(name)).thenReturn(Single.error(failure))
        dogImageRepository.getSimilarDogImages(name).test().assertError(failure)
        verify(dogImageAPI).getSimilarDogImages(name)
    }
}