package com.example.randomdogimages

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.example.randomdogimages.databinding.ActivityMainBinding
import com.example.randomdogimages.network.RetrofitAdapter
import com.example.randomdogimages.utils.Status
import com.example.randomdogimages.utils.ViewUtils
import com.example.randomdogimages.utils.ViewUtils.toBitmap
import com.example.randomdogimages.viewmodel.MainViewModel
import com.example.randomdogimages.viewmodel.ViewModelFactory
import kotlinx.coroutines.*
import java.net.URL

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
                            lifecycleScope.launch {

                            val imageLink = response.data?.message
                            val url = URL(imageLink)
                             binding.ivDogImage.load(imageLink)

                             val result= async(context = Dispatchers.IO) {url.toBitmap()}
                                 result.await()?.let { ViewUtils.createPaletteSync(it).swatches}?.forEach {
                                    binding.container.setCardBackgroundColor(it.bodyTextColor)
                                    binding.btnGetImage.setBackgroundColor(it.rgb)
                                    val colorDrawable =  ColorDrawable(it.rgb)
                                    supportActionBar?.setBackgroundDrawable(colorDrawable)
                                     window.statusBarColor = it.rgb
                                }

}




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