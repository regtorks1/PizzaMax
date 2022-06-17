package com.example.pizzamax.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.databinding.FragmentBigBetterBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.*
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.BigBetterAdapter
import com.example.pizzamax.views.ui.activity.CheckoutActivity
import com.example.pizzamax.views.ui.activity.DetailsActivity
import com.example.pizzamax.views.ui.activity.FavoritesActivity
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.imgUrl
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.price
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.size
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.type
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class BigBetterFragment : Fragment(), AdapterListImpl {

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }

    companion object {
        fun newInstance() = AppetizersFragment()
    }

    private lateinit var binding: FragmentBigBetterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBigBetterBinding.inflate(layoutInflater)
        val bindingMainActivity = (activity as MainActivity).binding

        val recyclerAdapter: BigBetterAdapter by lazy {
            BigBetterAdapter(
                this
            ) {title, price -> mainAlertDialog(title, price){
                bindingMainActivity.linearViewCart.visibility = View.VISIBLE
                bindingMainActivity.nextView.visibility = View.VISIBLE
                bindingMainActivity.viewCart.visibility = View.VISIBLE
            }

            }
        }  //initialize adapter
        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)

        productViewmodel.getAllFromBigBetter.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                recyclerAdapter.submitList(it)
            }
        })
        return binding.root
    }

    override fun onAddCart(cart: ValuesDeals) {
    }

    override fun onAddCart(cart: Favorites) {
        TODO("Not yet implemented")
    }

    override fun onAddCart(cart: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onAddCart(cart: BigBetter) {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }

    override fun addToFavorites(favorites: ValuesDeals) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: BigBetter) {
        val list = listOf(
            Favorites(
                imgUrl = favorites.imgUrl,
                price = favorites.price,
                size = favorites.size
            )
        )
        productViewmodel.insertIntoFavorites(list)
        startActivity(Intent(requireContext(), FavoritesActivity::class.java))
    }

    override fun addToFavorites(favorites: SignaturePizza) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: ValuesDeals) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: BigBetter) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(type, "details")
        intent.putExtra(imgUrl, details.imgUrl)
        intent.putExtra(size, details.size)
        intent.putExtra(price, details.price)
        startActivity(intent)
    }

    override fun onDetailsOnItemClicked(details: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onDetailsOnItemClicked(details: SignaturePizza) {
        TODO("Not yet implemented")
    }

}