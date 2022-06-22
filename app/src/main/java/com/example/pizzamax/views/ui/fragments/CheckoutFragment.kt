package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.ActivityCheckoutBinding
import com.example.pizzamax.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater)
         (activity as MainActivity).binding.linearViewCart.visibility = View.GONE

        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

}