package com.example.pizzamax.views.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.load
import com.example.pizzamax.MainActivity
import com.example.pizzamax.databinding.ActivityDetailsBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.views.util.mainAlertDialog

class DetailsActivity : AppCompatActivity() {
    private val productViewmodel: ActivityViewmodel by viewModels {
        ActivityViewModelFactory((application as App).productRepository)
    }
    private  var activity: AppCompatActivity?=null
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")
        if (type.equals("details")) {
            val size = intent.getStringExtra("size")
            val priceString = intent.getStringExtra("price")
            val imgUrl = intent.getStringExtra("imgUrl")

             binding.addCart.setOnClickListener {
                 mainAlertDialog(title = size!!, price = priceString!!){
                      (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
                 }
            }

            with(binding) {
                price.text = "Ghc " + priceString.toString()
                pizzaSize.text = size.toString()
                posterBanner.load(imgUrl)
            }
        }
    }
}