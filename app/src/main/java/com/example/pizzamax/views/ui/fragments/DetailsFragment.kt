package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentDetailsBinding
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment.Companion.size
import com.example.pizzamax.views.util.mainAlertDialog

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.addCart.setOnClickListener {
                mainAlertDialog(title = "Nothing", price = "Nothing"){
                    (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
                }
            }
         with(binding) {
                price.text = "Ghc " + arguments?.getString("price")
                pizzaSize.text = arguments?.getString("size")
                posterBanner.load(arguments?.getString("imgUrl"))
            }
        return binding.root
    }


}