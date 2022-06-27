package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentCheckoutBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.CartAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutFragment : Fragment(), CartAdapter.CartImpl {
    private lateinit var binding: FragmentCheckoutBinding

    private val productViewmodel: ProductViewModel by viewModels ()

    private var cartItems = mutableListOf<Cart>()
    private var totalCartPrice: Int = 0
    private var quantity: Int = 0
    private var newVal: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater)
        (activity as MainActivity).binding.linearViewCart.visibility = View.GONE

        spinnersSetUp()
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_homeFragment)
        }
        binding.delete.setOnClickListener {
            binding.subTotal.text = "0"
            binding.grandTotal1.text = "0"
        }

        val cartAdapter: CartAdapter by lazy { CartAdapter(this) }
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = cartAdapter
        thisRecycler.layoutManager = LinearLayoutManager(requireContext())

        productViewmodel.getAllFromCart.observe(viewLifecycleOwner, Observer { list ->
            lifecycleScope.launch {
                list?.let {
                    cartAdapter.submitList(it)
                    cartItems.clear()
                    totalCartPrice = 0
                    cartItems.addAll(list)
                }
                cartItems.forEach {
                    totalCartPrice += it.price.toInt()
                    quantity += it.quantity.toInt()
                    newVal += it.quantityPrice.toInt()
                }
                binding.subTotal.text = totalCartPrice.toString()
                Log.d("CART", ":::::::::::::::::::${totalCartPrice}")
                Log.d("QUANTITY", ":::::::::::::::::::${quantity}")

            }
        })

        return binding.root
    }


    private fun spinnersSetUp() {
        //Spinner
        val firstSpinner: Spinner = binding.spinnerSelectPayment
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.payment_method,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            firstSpinner.adapter = adapter
        }

        val secondSpinner: Spinner = binding.spinnerSelectTime
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.time_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            secondSpinner.adapter = adapter
        }
    }

    override fun viewAllCartList(cart: Cart) {
        // findNavController().navigate(R.id.action_checkoutFragment_to_cartFragment)
    }

}