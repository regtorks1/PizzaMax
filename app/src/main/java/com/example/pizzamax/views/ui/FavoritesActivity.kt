package com.example.pizzamax.views.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.databinding.ActivityFavoritesBinding
import com.example.pizzamax.viewmodel.ProductViewModel

class FavoritesActivity : AppCompatActivity() {
    lateinit var deal1: TextView
    lateinit var desc: TextView
    lateinit var priceAmt: TextView

    private lateinit var binding: ActivityFavoritesBinding
    private var favoriteID = -1

    lateinit var productViewModel: ProductViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}
