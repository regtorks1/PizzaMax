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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentBigBetterBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.CategoriesList
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ProductListAdapter
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import com.example.pizzamax.views.ui.activity.CheckoutActivity
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
    private lateinit var binding: FragmentBigBetterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBigBetterBinding.inflate(layoutInflater)
        val bindingMainActivity = (activity as MainActivity).binding

        val recyclerAdapter: ProductListAdapter by lazy {
            ProductListAdapter(this) { title, price ->
                mainAlertDialog(title, price) {
                    bindingMainActivity.linearViewCart.visibility = View.VISIBLE
                }
            }
        }   //initialize adapter
        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(context)

        productViewmodel.getCategoriesList("better").observe(viewLifecycleOwner, Observer { list ->
            lifecycleScope.launch {
                list.forEach {
                    recyclerAdapter.submitList(it.list)
                }
            }
        })
        return binding.root
    }

    override fun onAddToCartListener(cart: Cart) {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.quantity)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }

    override fun onAddToFavoriteListener(favorites: Favorites) {
        val list = listOf(
            ProductRecyclerViewItem.Favorites(
                imgUrl = favorites.imgUrl,
                price = favorites.price,
                size = favorites.size
            )
        )
        //  productViewmodel.insertIntoFavorites(list)
        findNavController().navigate(R.id.action_bigBetterFragment_to_favoritesFragment)
    }

    override fun onViewDetailListener(categoriesList: CategoriesList) {
        val bundle = Bundle()
        bundle.putString(type, "details")
        bundle.putString(imgUrl, categoriesList.imgUrl)
        bundle.putString(size, categoriesList.size)
        bundle.putString(price, categoriesList.price)
        findNavController().navigate(R.id.action_bigBetterFragment_to_detailsFragment, bundle)
    }


}