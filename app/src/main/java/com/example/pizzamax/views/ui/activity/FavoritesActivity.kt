package com.example.pizzamax.views.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.databinding.ActivityFavoritesBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity(), FavoritesAdapter.OnFavoriteDetailPage {
    private lateinit var binding: ActivityFavoritesBinding

    private val activity: AppCompatActivity? = null

    private val productViewmodel: ActivityViewmodel by viewModels {
        ActivityViewModelFactory((application as App).productRepository)
    }

    private val favoritesAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter(this) { title, price ->
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

    override fun viewDetail(favorites: Favorites) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("type", "details")
        intent.putExtra("imgUrl", favorites.imgUrl)
        intent.putExtra("size", favorites.size)
        intent.putExtra("price", favorites.price)
        startActivity(intent)
    }

    override fun onItemRemoveClick(item: Favorites) {
        productViewmodel.deleteFavorite(item)
    }

    override fun onDeleteAllClick(position: Int) {
         val list = favoritesAdapter.currentList.toMutableList()
         val id = list[position].id
        val size = list[position].size
        val price = list[position].price
        val imgUrl = list[position].imgUrl
        lifecycleScope.launch {
          //  productViewmodel.deleteFavorite()
        }

    }

}