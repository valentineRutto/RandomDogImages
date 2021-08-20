package com.example.randomdogimages.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import android.view.Window
import coil.api.load
import com.example.randomdogimages.R
import com.example.randomdogimages.databinding.ActivityBreedsDetailsBinding
import com.example.randomdogimages.databinding.ActivityBreedsDetailsBinding.inflate
import com.example.randomdogimages.databinding.ActivityMainBinding
import com.example.randomdogimages.databinding.ActivityMainBinding.inflate
import com.example.randomdogimages.databinding.RowBreedsBinding.inflate

import com.example.randomdogimages.network.data.BreedUiData
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class BreedsDetails : AppCompatActivity() {
    lateinit var binding: ActivityBreedsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        binding = ActivityBreedsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val breedId = intent.getIntExtra("breedId", 0)
        val breed = intent.getSerializableExtra("breed",) as BreedUiData


        binding.ivDogImage.load("${breed.imageUrl}") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }
        binding.txtBreedName.text = breed.name
        binding.txtBredFor.text = breed.bredFor

    }

}