package com.example.randomdogimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.randomdogimages.databinding.ActivityMainBinding
import com.example.randomdogimages.network.RetrofitAdapter
import com.example.randomdogimages.utils.Status
import com.example.randomdogimages.viewmodel.MainViewModel
import com.example.randomdogimages.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
        setupViewModel()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(
                        RetrofitAdapter.apiClient
                )
        ).get(MainViewModel::class.java)

    }

    fun setOnClickListeners(){

        binding.btnGetImage.setOnClickListener {

            mainViewModel.getRandomDogImage().observe(this, Observer {
                    response ->

                response?.let { resource ->

                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.ivDogImage.load(response.data?.message)
                        }
                        Status.LOADING -> {
                            Toast.makeText(this, "Loading ... ", Toast.LENGTH_LONG).show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }


}