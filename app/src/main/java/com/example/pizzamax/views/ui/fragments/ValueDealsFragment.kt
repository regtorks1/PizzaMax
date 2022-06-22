package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.pizzamax.databinding.FragmentValueDealsBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
<<<<<<< HEAD
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoriesList
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.*
import com.example.pizzamax.views.ui.activity.CheckoutActivity
=======
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductListAdapter
>>>>>>> 8b8374c1d943034c3f75ba58207db097e9ed0a3b
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class ValueDealsFragment : Fragment(), AdapterListImpl {

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }
    private lateinit var binding: FragmentValueDealsBinding
    var listArray : ArrayList<CategoriesList> = ArrayList()
    private lateinit var iWant: Categories

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValueDealsBinding.inflate(layoutInflater)
        val bindingMainActivity = (activity as MainActivity).binding
<<<<<<< HEAD



        val recyclerAdapter: ProductListAdapter by lazy {
            ProductListAdapter(this) { title, price ->
                mainAlertDialog(title, price) {
                    productViewmodel.getCategoryList.observe(viewLifecycleOwner, Observer { list ->
                         list.forEach {
                             //item = it.quantity.toInt()
                             //amount += it.price.toInt()
                             //bindingMainActivity.itemNumber.text = "${it.quantity} Items"
                             bindingMainActivity.amount.text = "Ghc ${it.price}"
                         }

                         iterator += item
                         total += amount
                         Log.d("TOTAL ITEM", "::::::::::::::::::::::$iterator")
                         Log.d("Total Amt", ":::::::::::::::::::::::${(amount)}")
                     })
=======
        val recyclerAdapter: ProductListAdapter by lazy {
            ProductListAdapter(this) { title, price ->
                mainAlertDialog(title, price!!) {
                    productViewmodel.getAllFromCart.observe(viewLifecycleOwner, Observer { list ->
                        list.forEach {
                            item = it.quantity.toInt()
                            amount += it.price.toInt()
                            bindingMainActivity.itemNumber.text = "${it.quantity} Items"
                            bindingMainActivity.amount.text = "Ghc ${it.price}"
                        }
                        iterator += item
                        total += amount
                    })
                    bindingMainActivity.linearViewCart.visibility = View.VISIBLE
>>>>>>> 8b8374c1d943034c3f75ba58207db097e9ed0a3b
                    bindingMainActivity.viewCart.visibility = View.VISIBLE
                    bindingMainActivity.nextView.visibility = View.VISIBLE
                }
            }
        }  //initialize adapter

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)
<<<<<<< HEAD
        productViewmodel.getCategoryList.observe(viewLifecycleOwner, Observer {list->
                 recyclerAdapter.submitList(list)
=======
        productViewmodel.getCategoriesList("deals").observe(viewLifecycleOwner, Observer { list ->
            Log.d("DEALS", "$list")
            lifecycleScope.launch {
                list.forEach {
                    recyclerAdapter.submitList(it.list)
                }
            }
>>>>>>> 8b8374c1d943034c3f75ba58207db097e9ed0a3b
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


    companion object {
        const val price = "price"
        const val size = "size"
        const val imgUrl = "imgUrl"
        const val type = "type"
        var item = 0
        var amount = 0
        var iterator = 0
        var total = 0
    }
}