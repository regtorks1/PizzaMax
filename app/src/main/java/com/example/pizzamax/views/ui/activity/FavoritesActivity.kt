package com.example.pizzamax.views.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.databinding.ActivityFavoritesBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.util.mainAlertDialog

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    private val activity: AppCompatActivity?=null

    private val productViewmodel: ActivityViewmodel by viewModels {
        ActivityViewModelFactory((application as App).productRepository)
    }

    private val favoritesAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter { title, price ->
            mainAlertDialog(title, price) {
                (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = favoritesAdapter
        thisRecycler.layoutManager = LinearLayoutManager(this)

        productViewmodel.getAllFavorites.observe(this, Observer {
            favoritesAdapter.submitList(it)
        })

    }

}