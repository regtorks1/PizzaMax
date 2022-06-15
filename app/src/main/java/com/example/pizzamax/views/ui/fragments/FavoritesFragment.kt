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
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(), FavoritesAdapter.OnFavoriteDetailPage {
    private lateinit var binding: FragmentFavoritesBinding

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(layoutInflater)

      /*  fun visibility() = (activity as MainActivity).binding.apply {
            linearViewCart.visibility = View.VISIBLE
            nextView.visibility = View.VISIBLE
            viewCart.visibility = View.VISIBLE

        }*/


        val favoritesAdapter: FavoritesAdapter by lazy {
            FavoritesAdapter(this) { title, price ->
                mainAlertDialog(title, price) {
                   // visibility()
                  (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
                }
            }
        }


         //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = favoritesAdapter
        thisRecycler.layoutManager = LinearLayoutManager(requireContext())
        productViewmodel.getAllFavorites.observe(requireActivity(), Observer {
            favoritesAdapter.submitList(it)
        })

        return binding.root
    }

    override fun viewDetail(favorites: Favorites) {
        //TODO will pass safeArgs here
        findNavController().navigate(R.id.action_favoritesFragment_to_detailsFragment)
    }

    override fun onItemRemoveClick(position: Int) {
        /*val list = favoritesAdapter.currentList.toMutableList()
        *//*val id = list[position].id
        val size = list[position].size
        val price = list[position].price
        val imgUrl = list[position].imgUrl*//*
        lifecycleScope.launch {
            productViewmodel.deleteFavorite(list)
        }*/
    }

}