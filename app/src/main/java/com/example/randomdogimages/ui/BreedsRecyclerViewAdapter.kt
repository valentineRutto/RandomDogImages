package com.example.randomdogimages.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.randomdogimages.R
import com.example.randomdogimages.databinding.RowBreedsBinding
import com.example.randomdogimages.network.data.BreedUiData
import com.google.android.material.card.MaterialCardView
interface BreedsClickListener{
    fun onBreedClicked(item: BreedUiData,breedCard: CardView)
}
class BreedsRecyclerViewAdapter(var breedClickListener:BreedsClickListener) :   ListAdapter<BreedUiData, BreedsRecyclerViewAdapter.ViewHolder>(BreedsDiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item,breedClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RowBreedsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: BreedUiData,breedClickListener:BreedsClickListener) {

            binding.ivDogImage.load("${item.imageUrl}") {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }

            binding.txtBreedName.text = item.name

            binding.container.setOnClickListener{
                breedClickListener.onBreedClicked(item,binding.container)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowBreedsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}


object BreedsDiffCallback : DiffUtil.ItemCallback<BreedUiData>() {

    override fun areItemsTheSame(oldItem: BreedUiData, newItem: BreedUiData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BreedUiData, newItem: BreedUiData): Boolean {
        return oldItem == newItem
    }

}
