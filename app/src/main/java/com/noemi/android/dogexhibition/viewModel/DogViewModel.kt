package com.noemi.android.dogexhibition.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noemi.android.dogexhibition.api.model.Breed
import com.noemi.android.dogexhibition.api.model.BreedImages
import com.noemi.android.dogexhibition.api.model.DogBreedResult
import com.noemi.android.dogexhibition.api.model.RandomImageResult
import com.noemi.android.dogexhibition.api.remoteDataSource.DogBreedService
import com.noemi.android.dogexhibition.api.remoteDataSource.DogImageService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DogViewModel @Inject constructor(
    private val dogBreedService: DogBreedService,
    private val dogImageService: DogImageService
) : DogBaseViewModel() {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val dogBreeds: MutableLiveData<MutableList<Breed>> = MutableLiveData<MutableList<Breed>>()
    val randomDogImages: MutableLiveData<MutableList<BreedImages>> =
        MutableLiveData<MutableList<BreedImages>>()
    val emptyRandomImage = MutableLiveData<Boolean>()

    fun getDogBreeds() {
        compositeDisposable.clear()
        val disposable = dogBreedService.getDogBreedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                progress.value = true
            }.doFinally {
                progress.value = false
            }.subscribeWith(object : DisposableSingleObserver<DogBreedResult>() {
                override fun onSuccess(result: DogBreedResult) {
                    Log.d(TAG, "onSuccess() - ${result.message.size}")

                    val breedList = result.message

                    if (breedList.isEmpty())
                        emptyRandomImage.value = true

                    val breeds = mutableListOf<Breed>()
                    breedList.forEachIndexed { index, name ->
                        breeds.add(Breed(id = index, breedName = name))
                    }
                    dogBreeds.value = breeds
                }

                override fun onError(error: Throwable) {
                    Log.e(TAG, "onError() - ${error.message}")
                    errorEvent.value = ERROR_MESSAGE
                }
            })
        compositeDisposable.add(disposable)
    }

    fun getRandomImages(breed: String) {
        compositeDisposable.clear()
        val disposable = dogImageService.getSimilarDogImages(breed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                progress.value = true
            }
            .doFinally {
                progress.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<RandomImageResult>() {
                override fun onSuccess(result: RandomImageResult) {
                    Log.d(TAG, "onSuccess() - ${result.message?.size}")
                    val images = mutableListOf<BreedImages>()
                    result.message.let {
                        it?.forEachIndexed { index, url ->
                            images.add(BreedImages(id = index, url = url))
                        }
                    }
                    randomDogImages.value = images
                }

                override fun onError(error: Throwable) {
                    Log.e(TAG, "onError() - ${error.message}")
                    errorEvent.value = ERROR_MESSAGE
                }
            })
        compositeDisposable.add(disposable)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val dogBreedService: DogBreedService,
        private val dogImageService: DogImageService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DogViewModel(dogBreedService, dogImageService) as T
        }
    }

    companion object {
        const val TAG = "DogViewModel"
        private const val ERROR_MESSAGE = "An error has occurred"
    }
}