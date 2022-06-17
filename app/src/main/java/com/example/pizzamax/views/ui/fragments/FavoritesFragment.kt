package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentFavoritesBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.*
import com.example.pizzamax.viewmodel.*
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.adapters.ProductRecyclerViewAdapter
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(), AdapterListImpl {
    private lateinit var binding: FragmentFavoritesBinding

    private val productViewmodel: ProductListViewModel by viewModels {
        ProductListViewModelFactory((activity?.application as App).productRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
         val bindingMainActivity = (activity as MainActivity).binding

       val recyclerAdapter: ProductRecyclerViewAdapter by lazy {
            ProductRecyclerViewAdapter(this){ title, price ->
                mainAlertDialog(title, price){
                    bindingMainActivity.linearViewCart.visibility = View.VISIBLE
                }
            }
        }
         //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(requireContext())
        productViewmodel.getAllFavorites.observe(requireActivity(), Observer {
            recyclerAdapter.items = it
        })

        return binding.root
    }

    override fun onAddCart(cart: ValuesDeals) {
        TODO("Not yet implemented")
    }

    override fun onAddCart(cart: Favorites) {
        TODO("Not yet implemented")
    }

    override fun onAddCart(cart: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onAddCart(cart: BigBetter) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: ValuesDeals) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: BigBetter) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: SignaturePizza) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: ValuesDeals) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: BigBetter) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: SignaturePizza) {
        TODO("Not yet implemented")
    }


}