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
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductListAdapter
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class ValueDealsFragment : Fragment(), AdapterListImpl {

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }
    private lateinit var binding: FragmentValueDealsBinding
    private var cartItems = arrayListOf<Cart>()
    private var totalCartPrice: Int = 0
    private var quantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingMainActivity = (activity as MainActivity).binding
        bindingMainActivity.itemNumber.text = "$quantity Items"
        bindingMainActivity.amount.text = "Ghc $totalCartPrice"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValueDealsBinding.inflate(layoutInflater)
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
        productViewmodel.getCategoryWithItems(1).observe(viewLifecycleOwner, Observer { list ->
            Log.d("DEALS", "$list")
            lifecycleScope.launch {
                list.forEach {
                    recyclerAdapter.submitList(it.categoryItems)
                }
            }
        })

        productViewmodel.getAllFromCart.observe(viewLifecycleOwner, Observer { list ->
            cartItems.clear()
            cartItems.addAll(list)
            cartItems.forEach {
                totalCartPrice += it.price.toInt()
                quantity += it.quantity.toInt()
                bindingMainActivity.itemNumber.text = "$quantity Items"
                bindingMainActivity.amount.text = "Ghc $totalCartPrice"
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