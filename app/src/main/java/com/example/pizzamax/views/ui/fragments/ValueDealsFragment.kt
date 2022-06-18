package com.example.pizzamax.views.ui.fragments

import android.content.Intent
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
import com.example.pizzamax.databinding.FragmentValueDealsBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.*
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductRecyclerViewAdapter
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import com.example.pizzamax.views.ui.activity.CheckoutActivity
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class ValueDealsFragment : Fragment(), AdapterListImpl {

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }
    private lateinit var binding: FragmentValueDealsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValueDealsBinding.inflate(layoutInflater)
        val bindingMainActivity = (activity as MainActivity).binding

        val recyclerAdapter: ProductRecyclerViewAdapter by lazy {
            ProductRecyclerViewAdapter(this) { title, price ->
                mainAlertDialog(title, price) {
                    /* productViewmodel.getAllFromCart.observe(viewLifecycleOwner, Observer { list ->
                         list.forEach {
                             item = it.quantity.toInt()
                             amount += it.price.toInt()
                             bindingMainActivity.itemNumber.text = "${it.quantity} Items"
                             bindingMainActivity.amount.text = "Ghc ${it.price}"
                         }

                         iterator += item
                         total += amount
                         Log.d("TOTAL ITEM", "::::::::::::::::::::::$iterator")
                         Log.d("Total Amt", ":::::::::::::::::::::::${(amount)}")
                     })*/
                    bindingMainActivity.viewCart.visibility = View.VISIBLE
                    bindingMainActivity.nextView.visibility = View.VISIBLE
                }
            }
        }  //initialize adapter


        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)
        productViewmodel.getCategoriesList("deals").observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                it.size
                recyclerAdapter.items = it
            }
        })

        return binding.root
    }


    override fun onAddCart(cart: ValuesDeals) {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        intent.putExtra(type, "cart")
        intent.putExtra(size, cart.size)
        intent.putExtra(price, cart.price)
        startActivity(intent)
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
        val list = listOf(
            ProductRecyclerViewItem.Favorites(
                imgUrl = favorites.imgUrl!!,
                price = favorites.price!!,
                size = favorites.size!!
            )
        )
        // productViewmodel.insertIntoFavorites(list)
    }


    override fun onDetailsOnItemClicked(details: ValuesDeals) {
        val bundle = Bundle()
        bundle.putString(type, "details")
        bundle.putString(imgUrl, details.imgUrl)
        bundle.putString(size, details.size)
        bundle.putString(price, details.price)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
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

    override fun addToFavorites(favorites: BigBetter) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: SignaturePizza) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: Appetizers) {
        TODO("Not yet implemented")
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