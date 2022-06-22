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
import com.example.pizzamax.databinding.FragmentAppetizersBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductListAdapter
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.imgUrl
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.price
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.size
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.type
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class AppetizersFragment : Fragment(), AdapterListImpl {

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }
    private lateinit var binding: FragmentAppetizersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppetizersBinding.inflate(layoutInflater)
        val bindingMainActivity = (activity as MainActivity).binding
        val recyclerAdapter: ProductListAdapter by lazy {
            ProductListAdapter(this) { title, price ->
                mainAlertDialog(title, price!!) {
                    bindingMainActivity.linearViewCart.visibility = View.VISIBLE
                }
            }
        }  //initialize adapter
        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)

        productViewmodel.getCategoriesList("appetizer")
            .observe(viewLifecycleOwner, Observer { list ->
<<<<<<< HEAD
                    list.f {
                         recyclerAdapter.submitList(it.list?.toList())
                         Log.d("APPETIZERS", "::::::::::::::${it.list}")
=======
                lifecycleScope.launch {
                    list.forEach {
                        recyclerAdapter.submitList(it.list?.toList())
>>>>>>> 8b8374c1d943034c3f75ba58207db097e9ed0a3b
                    }
            })
        return binding.root
    }

    override fun onAddToCartListener(cart: Cart) {
        findNavController().navigate(R.id.action_homeFragment_to_checkoutFragment)
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
        val bundle = Bundle()
        bundle.putString(type, "details")
        bundle.putString(imgUrl, categoryItems.imgUrl)
        bundle.putString(size, categoryItems.size)
        bundle.putString(price, categoryItems.price)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }


}