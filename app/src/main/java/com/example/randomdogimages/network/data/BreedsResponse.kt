package com.example.randomdogimages.network.data


import com.google.gson.annotations.SerializedName

class BreedsResponse : ArrayList<BreedsResponse.BreedsResponseItem>(){
    data class BreedsResponseItem(
        @SerializedName("bred_for")
        val bredFor: String?,
        @SerializedName("breed_group")
        val breedGroup: String?,
        @SerializedName("country_code")
        val countryCode: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("height")
        val height: Height?,
        @SerializedName("history")
        val history: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image")
        val image: Image?,
        @SerializedName("life_span")
        val lifeSpan: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("origin")
        val origin: String?,
        @SerializedName("reference_image_id")
        val referenceImageId: String?,
        @SerializedName("temperament")
        val temperament: String?,
        @SerializedName("weight")
        val weight: Weight?
    ) {
        data class Height(
            @SerializedName("imperial")
            val imperial: String?,
            @SerializedName("metric")
            val metric: String?
        )
    
        data class Image(
            @SerializedName("height")
            val height: Int?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("url")
            val url: String?,
            @SerializedName("width")
            val width: Int?
        )
    
        data class Weight(
            @SerializedName("imperial")
            val imperial: String?,
            @SerializedName("metric")
            val metric: String?
        )
    }
}