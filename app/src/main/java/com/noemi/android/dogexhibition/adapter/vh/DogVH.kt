package com.noemi.android.dogexhibition.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noemi.android.dogexhibition.adapter.adapter.DogClickListener
import com.noemi.android.dogexhibition.api.model.Breed
import com.noemi.android.dogexhibition.util.OnTimeClickListener
import kotlinx.android.synthetic.main.item_dog_breed.view.*

class DogVH(
    private val view: View,
    private val dogClickListener: DogClickListener?
) : RecyclerView.ViewHolder(view) {

    fun bind(dogBreed: Breed, holder: DogVH) {
        view.tvDogBreed.text = dogBreed.breedName
        itemView.setOnClickListener(object : OnTimeClickListener() {
            override fun onViewClicked(v: View) {
                val index = holder.absoluteAdapterPosition
                dogClickListener?.invoke(dogBreed.breedName, index)
            }
        })
    }
}