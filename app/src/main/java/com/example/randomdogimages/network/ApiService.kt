package com.example.randomdogimages.network

import com.example.randomdogimages.network.data.BreedsResponse
import com.example.randomdogimages.network.data.DogImatge
import retrofit2.http.GET

interface ApiService {

    @GET("breeds")
    suspend fun getDogBreeds() : BreedsResponse
}