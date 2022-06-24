package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentDetailsBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.util.mainAlertDialog

class DetailsFragment : Fragment(), AdapterListImpl {
    private lateinit var binding: FragmentDetailsBinding

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)


        with(binding) {
            price.text = "Ghc " + arguments?.getString("price")
            pizzaSize.text = arguments?.getString("size")
            posterBanner.load(arguments?.getString("imgUrl"))
            val priceTv = binding.price.text.toString()
            val sizeTv = binding.pizzaSize.text
            val name = binding.deal.text.toString()

            addCart.setOnClickListener {
                mainAlertDialog(title = name, price = priceTv.drop(4)) {
                    (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
                }
            }
        }
        return binding.root
    }

    override fun onAddToCartListener(cart: Cart) {
        findNavController().navigate(R.id.action_detailsFragment_to_cartFragment)
    }

    override fun onAddToFavoriteListener(favorites: CategoryItems) {
        val list = listOf(
            Favorites(
                imgUrl = favorites.imgUrl!!,
                price = favorites.price!!,
                size = favorites.size!!
            )
        )
        productViewmodel.insertIntoFavorites(list)
    }

    override fun onViewDetailListener(categoryItems: CategoryItems) {
        Log.d("DETAIL", "NOT IMPLEMENTED")
    }


}