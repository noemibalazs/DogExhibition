package com.noemi.android.dogexhibition.adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.noemi.android.dogexhibition.R
import com.noemi.android.dogexhibition.adapter.difUtil.DogImageUtil
import com.noemi.android.dogexhibition.adapter.vh.DogImageVH
import com.noemi.android.dogexhibition.api.model.BreedImages

class DogImageAdapter : ListAdapter<BreedImages, DogImageVH>(DogImageUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogImageVH(view)
    }

    override fun onBindViewHolder(holder: DogImageVH, position: Int) {
        holder.bind(getItem(position))
    }
}