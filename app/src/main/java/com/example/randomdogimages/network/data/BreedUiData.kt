package com.example.randomdogimages.network.data

import java.io.Serializable

data class BreedUiData(
        val id: Int?,
        val name: String?,
        val bredFor: String?,
        val lifeSpan: String?,
        val temperament: String?,
        val imageId: String?,
        val imageUrl: String?
) : Serializable