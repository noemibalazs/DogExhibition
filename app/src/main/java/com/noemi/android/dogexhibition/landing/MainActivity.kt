package com.noemi.android.dogexhibition.landing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.noemi.android.dogexhibition.R
import com.noemi.android.dogexhibition.SAVED_INSTANCE_KEY
import com.noemi.android.dogexhibition.adapter.adapter.DogAdapter
import com.noemi.android.dogexhibition.adapter.adapter.DogClickListener
import com.noemi.android.dogexhibition.api.model.Breed
import com.noemi.android.dogexhibition.app.App
import com.noemi.android.dogexhibition.pref.PreferencesRepository
import com.noemi.android.dogexhibition.searching.RandomDogExhibitionActivity
import com.noemi.android.dogexhibition.viewModel.DogViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dogViewModel: DogViewModel

    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    private val dogAdapter: DogAdapter by lazy {
        DogAdapter(dogClickListener)
    }

    private val dogClickListener: DogClickListener = { name, index ->
        launchRandomActivity(name)
        preferencesRepository.index = index
        Log.d(TAG, "RV index is: $index")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val breeds = savedInstanceState.getParcelableArrayList<Breed>(SAVED_INSTANCE_KEY)
            dogAdapter.submitList(breeds)
        }

        setUpRv()
        initObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val list = dogAdapter.currentList
        val breeds = arrayListOf<Breed>()
        breeds.addAll(list)
        outState.putParcelableArrayList(SAVED_INSTANCE_KEY, breeds)
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        rvDogBreeds.layoutManager?.scrollToPosition(preferencesRepository.index)
        val index = preferencesRepository.index
        Log.d(TAG, "onStart() - the index to scroll: $index")
    }

    private fun setUpRv() {
        rvDogBreeds.adapter = dogAdapter
    }

    private fun initObservers() {
        dogViewModel.getDogBreeds()

        dogViewModel.dogBreeds.observe(this, Observer {
            dogAdapter.submitList(it)
        })

        dogViewModel.progress.observe(this, Observer {
            progressBar.isVisible = it
        })

        dogViewModel.errorEvent.observe(this, Observer {
            showErrorToastToUser(it)
        })
    }

    private fun launchRandomActivity(name: String) {
        val intent = Intent(this, RandomDogExhibitionActivity::class.java).apply {
            putExtra(KEY_DOG_BREED_NAME, name)
        }
        startActivity(intent)
    }

    private fun showErrorToastToUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val KEY_DOG_BREED_NAME = "breed_name"
        private const val TAG = "MainActivity"
    }
}