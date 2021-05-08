package com.example.randomdogimages.network

import retrofit2.http.GET

interface ApiService {

    @GET("api/breeds/image/random")
    suspend fun getRandomDogImage() : DogImatge
}