package com.example.pizzamax.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.pizzamax.R
import com.example.pizzamax.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
     private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val type = intent.getStringExtra("type")
        if (type.equals("cart")) {
            val size = intent.getStringExtra("size")
            val priceString = intent.getStringExtra("price")
            val imgUrl = intent.getStringExtra("imgUrl")

            with(binding) {
                price.text = priceString.toString()
                description.text = size.toString()
                posterBanner.load(imgUrl)
            }
        }
    }
}