package com.noemi.android.dogexhibition.searching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.noemi.android.dogexhibition.R
import com.noemi.android.dogexhibition.adapter.adapter.DogImageAdapter
import com.noemi.android.dogexhibition.app.App
import com.noemi.android.dogexhibition.landing.MainActivity
import com.noemi.android.dogexhibition.viewModel.DogViewModel
import kotlinx.android.synthetic.main.activity_random_dog_exhibition.*
import kotlinx.android.synthetic.main.activity_random_dog_exhibition.progressBar
import javax.inject.Inject

class RandomDogExhibitionActivity : AppCompatActivity() {

    @Inject
    lateinit var dogViewModel: DogViewModel

    private val imageAdapter by lazy {
        DogImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(R.layout.activity_random_dog_exhibition)

        val breedName = intent.getStringExtra(MainActivity.KEY_DOG_BREED_NAME)
        breedName?.let {
            dogViewModel.getRandomImages(it)
        }

        setUpRV()
        initObservers()
    }

    private fun setUpRV() {
        rvDogExhibition.adapter = imageAdapter
    }

    private fun initObservers() {
        dogViewModel.randomDogImages.observe(this, Observer {
            imageAdapter.submitList(it)
        })

        dogViewModel.progress.observe(this, Observer {
            progressBar.isVisible = it
        })

        dogViewModel.errorEvent.observe(this, Observer {
            showErrorToastToUser(it)
        })

        dogViewModel.emptyRandomImage.observe(this, Observer { event ->
            if (event) {
                informUser(getString(R.string.txt_empty_random_images))
            }
        })
    }

    private fun showErrorToastToUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun informUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}