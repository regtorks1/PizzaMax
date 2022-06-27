package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.databinding.FragmentCartBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.CartAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CartFragment : Fragment(), CartAdapter.CartImpl {
      private val productViewmodel: ProductViewModel by viewModels ()

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater)
        val cartAdapter: CartAdapter by lazy {
            CartAdapter(this)
        }

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = cartAdapter
        thisRecycler.layoutManager = LinearLayoutManager(requireContext())
           productViewmodel.getAllFromCart.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                cartAdapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun viewAllCartList(cart: Cart) {
        TODO("Not yet implemented")
    }
}