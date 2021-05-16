package com.noemi.android.dogexhibition.adapter.difUtil

import androidx.recyclerview.widget.DiffUtil
import com.noemi.android.dogexhibition.api.model.Breed

class DogUtils : DiffUtil.ItemCallback<Breed>() {

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}