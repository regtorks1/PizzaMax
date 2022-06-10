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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.databinding.FragmentAppetizersBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AppetizersRecyclerAdapter
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.ui.CheckoutActivity
import kotlinx.coroutines.launch

class AppetizersFragment : Fragment(), AppetizersRecyclerAdapter.UpdateCheckout {

    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }

    companion object {
        fun newInstance() = AppetizersFragment()
    }

    private lateinit var binding: FragmentAppetizersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppetizersBinding.inflate(layoutInflater)
        val recyclerAdapter: AppetizersRecyclerAdapter by lazy {
            AppetizersRecyclerAdapter(this)
        }  //initialize adapter
        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)

        productViewmodel.getAllFromAppetizers.observe(viewLifecycleOwner, Observer { list ->
            lifecycleScope.launch {
                recyclerAdapter.submitList(list)
            }
        })
        return binding.root
    }

    override fun onAddCart(cart: Appetizers) {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }


}