package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pizzamax.databinding.FragmentCartBinding
import com.example.pizzamax.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartFragment : Fragment() {
    //    private val productViewmodel: ProductViewModel by viewModels {
//        ProductViewModelFactory((activity?.application as App).productRepository)
//    }
    private val productViewmodel: ProductViewModel by viewModel()

    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }
}