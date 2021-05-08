package com.example.randomdogimages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomdogimages.network.ApiService
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiService: ApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class Name")
    }
}