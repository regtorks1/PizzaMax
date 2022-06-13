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
import com.example.pizzamax.databinding.FragmentBigBetterBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.BigBetter
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.BigBetterAdapter
import com.example.pizzamax.views.ui.activity.CheckoutActivity
import kotlinx.coroutines.launch

class BigBetterFragment : Fragment(), BigBetterAdapter.UpdateCheckout {

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
        val recyclerAdapter: BigBetterAdapter by lazy {
            BigBetterAdapter(
                this
            )
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

    override fun onAddCart(cart: BigBetter) {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }

}