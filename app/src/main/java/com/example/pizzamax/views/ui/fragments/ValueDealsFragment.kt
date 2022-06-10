package com.example.pizzamax.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentValueDealsBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.ui.CheckoutActivity
import com.example.pizzamax.views.ui.DetailsActivity
import com.example.pizzamax.views.util.alertDialog_b
import com.example.pizzamax.views.util.returnDialog1
import kotlinx.coroutines.launch

class ValueDealsFragment : Fragment(), ValuesDealRecyclerAdapter.UpdateCheckout, ValuesDealRecyclerAdapter.ShowDetails {
    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }


    private lateinit var binding: FragmentValueDealsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValueDealsBinding.inflate(layoutInflater)

        val recyclerAdapter: ValuesDealRecyclerAdapter by lazy {
            ValuesDealRecyclerAdapter(this, this, this)
        }  //initialize adapter

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)
        productViewmodel.getList.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                recyclerAdapter.submitList(it)
            }
        })

        return binding.root
    }


    override fun onAddCart(cart: ValuesDeals) {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }


    override fun onDetailsOnItemClicked(cart: ValuesDeals) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("imgUrl",cart.imgUrl)
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }
}