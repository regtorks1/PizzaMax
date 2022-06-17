package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentSignaturePizzaBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.*
import com.example.pizzamax.viewmodel.ProductListViewModel
import com.example.pizzamax.viewmodel.ProductListViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductRecyclerViewAdapter
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.imgUrl
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.price
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.size
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.type
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class SignaturePizzaFragment : Fragment(), AdapterListImpl {

    private val productViewmodel: ProductListViewModel by viewModels {
        ProductListViewModelFactory((activity?.application as App).productRepository)
    }

    companion object {
        fun newInstance() = AppetizersFragment()
    }

    private lateinit var binding: FragmentSignaturePizzaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignaturePizzaBinding.inflate(layoutInflater)
        val bindingMainActivity = (activity as MainActivity).binding
       val recyclerAdapter: ProductRecyclerViewAdapter by lazy {
            ProductRecyclerViewAdapter(this){ title, price ->
                mainAlertDialog(title, price){
                    bindingMainActivity.linearViewCart.visibility = View.VISIBLE
                }
            }
        }  //initialize adapter
        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)

        productViewmodel.getAllFromSignature.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                 recyclerAdapter.items = it
            }
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
         val list = listOf(
            ProductRecyclerViewItem.Favorites(
                imgUrl = favorites.imgUrl,
                price = favorites.price,
                size = favorites.size
            )
        )
        productViewmodel.insertIntoFavorites(list)
        findNavController().navigate(R.id.action_signaturePizzaFragment_to_favoritesFragment)
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
      val bundle = Bundle()
        bundle.putString(type, "details")
        bundle.putString(imgUrl, details.imgUrl)
        bundle.putString(size, details.size)
        bundle.putString(price, details.price)
        findNavController().navigate(R.id.action_signaturePizzaFragment_to_favoritesFragment, bundle)
    }

}