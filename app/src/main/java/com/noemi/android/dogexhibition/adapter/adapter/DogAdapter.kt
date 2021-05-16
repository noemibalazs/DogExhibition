package com.noemi.android.dogexhibition.adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.noemi.android.dogexhibition.R
import com.noemi.android.dogexhibition.adapter.difUtil.DogUtils
import com.noemi.android.dogexhibition.adapter.vh.DogVH
import com.noemi.android.dogexhibition.api.model.Breed

typealias DogClickListener = (name: String, index: Int) -> Unit

class DogAdapter(private val dogClickListener: DogClickListener) :
    ListAdapter<Breed, DogVH>(DogUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dog_breed, parent, false)
        return DogVH(view, dogClickListener)
    }

    override fun onBindViewHolder(holder: DogVH, position: Int) {
        holder.bind(getItem(position), holder)
    }
}