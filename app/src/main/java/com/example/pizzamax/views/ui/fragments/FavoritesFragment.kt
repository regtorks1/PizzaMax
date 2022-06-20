package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.databinding.FragmentFavoritesBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.*
import com.example.pizzamax.viewmodel.*
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductListAdapter
import com.example.pizzamax.views.adapters.ProductRecyclerViewAdapter
import com.example.pizzamax.views.util.mainAlertDialog

class FavoritesFragment : Fragment(), AdapterListImpl {
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
         val bindingMainActivity = (activity as MainActivity).binding

       val recyclerAdapter: ProductListAdapter by lazy {
            ProductListAdapter(this){ title, price ->
                mainAlertDialog(title, price){
                    bindingMainActivity.linearViewCart.visibility = View.VISIBLE
                }
            }
        }
         //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(requireContext())
       /* productViewmodel.getAllFavorites.observe(requireActivity(), Observer {
            recyclerAdapter.items = it
        })*/

        return binding.root
    }

    override fun onAddToCartListener(cart: Cart) {
        TODO("Not yet implemented")
    }

    override fun onAddToFavoriteListener(favorites: Favorites) {
        TODO("Not yet implemented")
    }

    override fun onViewDetailListener(categoriesList: CategoriesList) {
        TODO("Not yet implemented")
    }


}