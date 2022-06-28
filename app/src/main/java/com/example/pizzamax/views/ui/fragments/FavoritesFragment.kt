package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentFavoritesBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.imgUrl
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.price
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.size
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.type
import com.example.pizzamax.views.util.mainAlertDialog

class FavoritesFragment : Fragment(), FavoritesAdapter.OnFavoriteDetailPage {
    private lateinit var binding: FragmentFavoritesBinding

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }

    private val recyclerAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter(this) { title, price ->
            mainAlertDialog(title, price) {
                (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(requireContext())
        productViewmodel.getAllFavorites.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.submitList(it)
        })

        return binding.root
    }

    override fun viewDetail(favorites: Favorites) {
      val bundle = bundleOf("fav" to favorites)
        bundle.putParcelable(type, favorites)
        findNavController().navigate(R.id.action_favoritesFragment_to_detailsFragment, bundle)
    }

    override fun onItemRemoveClick(position: Int) {
        val currentList = recyclerAdapter.currentList.toMutableList()
        currentList.removeAt(position)
        recyclerAdapter.submitList(currentList)
    }


}