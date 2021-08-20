package com.example.randomdogimages.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.example.randomdogimages.databinding.ActivityMainBinding
import com.example.randomdogimages.network.RetrofitAdapter
import com.example.randomdogimages.network.data.BreedUiData
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
lateinit var breedsRecyclerViewAdapter: BreedsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        breedsRecyclerViewAdapter = BreedsRecyclerViewAdapter(object : BreedsClickListener{
            override fun onBreedClicked(item:BreedUiData, breedCard: CardView) {
                val intent = Intent(this@MainActivity, BreedsDetails::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MainActivity, breedCard, item.id.toString()
                )
                val bundle = Bundle()
                bundle.putSerializable("breed", item)

                intent.putExtra("breedId", item.id.toString())
                intent.putExtras(bundle)

                startActivity(intent, options.toBundle())

            }
        })

        binding.rvBreed.adapter = breedsRecyclerViewAdapter


        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                RetrofitAdapter.apiClient
            )
        ).get(MainViewModel::class.java)
        setOnClickListeners()
    }

    fun setOnClickListeners(){


            mainViewModel.getBreeds().observe(this, Observer {
                    response ->

                response?.let { resource ->

                    when (resource.status) {
                        Status.SUCCESS -> {
                            lifecycleScope.launch {

                                val breedsList = response.data?.map {
                                    BreedUiData(
                                        id = it.id,
                                        name = it.name,
                                        bredFor = it.bredFor,
                                        temperament = it.temperament,
                                        imageId = it.image?.id,
                                        imageUrl = it.image?.url,
                                        lifeSpan = it.lifeSpan
                                    )
                                }
                                breedsRecyclerViewAdapter.submitList(breedsList)
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


