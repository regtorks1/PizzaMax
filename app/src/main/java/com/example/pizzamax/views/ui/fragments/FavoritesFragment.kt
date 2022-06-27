package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesAdapter.OnFavoriteDetailPage {
    private lateinit var binding: FragmentFavoritesBinding

    private val productViewmodel: ProductViewModel by viewModels ()

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
        val bundle = Bundle()
        bundle.putString(type, "details")
        bundle.putString(imgUrl, favorites.imgUrl)
        bundle.putString(size, favorites.size)
        bundle.putString(price, favorites.price)
        findNavController().navigate(R.id.action_favoritesFragment_to_detailsFragment, bundle)
    }

    override fun onItemRemoveClick(position: Int) {
        val currentList = recyclerAdapter.currentList.toMutableList()
        currentList.removeAt(position)
        recyclerAdapter.submitList(currentList)
    }

   /* private fun deleteIcon(): View {
        val view = layoutInflater.inflate(R.layout.recycler_list, null)
        val fv = view.findViewById<ImageView>(R.id.favorite_heart)
        fv.setImageResource(R.drawable.delete_favorite)
        return view
    }*/


}