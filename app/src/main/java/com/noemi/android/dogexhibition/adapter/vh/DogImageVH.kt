package com.noemi.android.dogexhibition.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noemi.android.dogexhibition.R
import com.noemi.android.dogexhibition.api.model.BreedImages
import kotlinx.android.synthetic.main.item_dog.view.*

class DogImageVH(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(breedImages: BreedImages) {
        Glide.with(view.context).load(breedImages.url).placeholder(R.drawable.wolves)
            .error(R.drawable.wolves).into(view.ivDog)
    }
}