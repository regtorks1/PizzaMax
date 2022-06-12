package com.example.pizzamax.views.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.ActivityFavoritesBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.FavoriteClickInterface
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment

class FavoritesActivity : AppCompatActivity() {
    lateinit var deal1: TextView
    lateinit var desc: TextView
    lateinit var priceAmt: TextView

    private lateinit var binding: ActivityFavoritesBinding
    private var favoriteID = -1

    lateinit var productViewModel:ProductViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

           deal1 = binding.deal
           desc = binding.description
           priceAmt = binding.price

        val favoriteType = intent.getStringExtra("favoriteType")
        if(favoriteType == "Edit"){
              val favoriteTitle = intent.getStringExtra("favoriteTitle")
              val favoriteDescription = intent.getStringExtra("favoriteDescription")
              val favoritePrice = intent.getStringExtra("favoritePrice")
             favoriteID = intent.getIntExtra("noteId", -1)
            deal1.setText(favoriteTitle)
            desc.setText(favoriteDescription)
            priceAmt.setText(favoritePrice)




        }

    }
}