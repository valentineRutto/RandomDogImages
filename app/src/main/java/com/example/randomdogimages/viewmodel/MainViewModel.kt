package com.example.randomdogimages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randomdogimages.network.ApiService
import com.example.randomdogimages.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val apiService: ApiService) : ViewModel() {
    fun getRandomDogImage() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getRandomDogImage()))

        } catch (exception: Exception) {

            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Unable to fetch random images"
                )
            )

        }
    }
}