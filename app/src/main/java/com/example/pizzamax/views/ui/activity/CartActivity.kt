package com.example.pizzamax.views.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.databinding.ActivityCartBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.CartAdapter
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity(), CartAdapter.CartImpl {

   /* private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((application as App).productRepository)
    }*/

    private val cartAdapter: CartAdapter by lazy {
        CartAdapter(this)
    }

    private lateinit var binding: ActivityCartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = cartAdapter
        thisRecycler.layoutManager = LinearLayoutManager(this)

      /*  productViewmodel.getAllFromCart.observe(this, Observer {
            lifecycleScope.launch {
                cartAdapter.submitList(it)
            }
        })*/
    }

    /* override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {
         // Inflate the layout for this fragment
         binding = FragmentCartBinding.inflate(layoutInflater)

         //recycler setup
         val thisRecycler = binding.recyclerView
         thisRecycler.adapter = cartAdapter
         thisRecycler.layoutManager = LinearLayoutManager(this)

         productViewmodel.getAllFromCart.observe(this, Observer {
             lifecycleScope.launch {
                 cartAdapter.submitList(it)
             }
         })
         return binding.root
     }*/

    override fun viewAllCartList(cart: Cart) {
        TODO("Not yet implemented")
    }


}