package com.noemi.android.dogexhibition.adapter.difUtil

import androidx.recyclerview.widget.DiffUtil
import com.noemi.android.dogexhibition.api.model.BreedImages

class DogImageUtil : DiffUtil.ItemCallback<BreedImages>() {

    override fun areContentsTheSame(oldItem: BreedImages, newItem: BreedImages): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: BreedImages, newItem: BreedImages): Boolean {
        return oldItem.url == newItem.url
    }
}