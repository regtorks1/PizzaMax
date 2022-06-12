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
import com.example.pizzamax.MainActivity
import com.example.pizzamax.databinding.FragmentValueDealsBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.*
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
<<<<<<< HEAD
import com.example.pizzamax.views.adapters.AdapterListImpl
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.ui.activity.CheckoutActivity
import com.example.pizzamax.views.ui.activity.DetailsActivity
import com.example.pizzamax.views.ui.activity.FavoritesActivity
import com.example.pizzamax.views.util.mainAlertDialog
import kotlinx.coroutines.launch

class ValueDealsFragment : Fragment(), AdapterListImpl {
=======
import com.example.pizzamax.views.adapters.FavoriteClickInterface
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.ui.CheckoutActivity
import com.example.pizzamax.views.ui.DetailsActivity
import com.example.pizzamax.views.ui.FavoritesActivity
import com.example.pizzamax.views.util.alertDialog_b
import com.example.pizzamax.views.util.returnDialog1

import kotlinx.coroutines.launch


class ValueDealsFragment : Fragment(), ValuesDealRecyclerAdapter.UpdateCheckout,  ValuesDealRecyclerAdapter.FavoriteClickInterface, ValuesDealRecyclerAdapter.ShowDetails {

>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
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
<<<<<<< HEAD
            ValuesDealRecyclerAdapter(this) { title, price ->
                mainAlertDialog(title, price) {
                    (activity as MainActivity).binding.linearViewCart.visibility = View.VISIBLE
                }
            }
=======

            ValuesDealRecyclerAdapter(this, this, this, this)



>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
        }  //initialize adapter

        //setting up recycler for favorites
//        val favoriteRv = binding.recyclerView
//        favoriteRv.layoutManager = LinearLayoutManager(requireContext())
//
//        val favoriteAdapter = FavoritesAdapter(requireContext(),this)
//        favoriteRv.adapter = favoriteAdapter

        //observing changes
        productViewmodel.getList.observe(viewLifecycleOwner) { list ->
            list.let {
                recyclerAdapter.updateList(it)
            }

        }

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
        intent.putExtra(type, "cart")
        intent.putExtra(size, cart.size)
        intent.putExtra(price, cart.price)
        startActivity(intent)
    }

    override fun onAddCart(cart: Favorites) {
        TODO("Not yet implemented")
    }

<<<<<<< HEAD
    override fun onAddCart(cart: Appetizers) {
        TODO("Not yet implemented")
    }

    override fun onAddCart(cart: BigBetter) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: ValuesDeals) {
        val list = listOf(
            Favorites(
                imgUrl = favorites.imgUrl,
                price = favorites.price,
                size = favorites.size
            )
        )
        productViewmodel.insertIntoFavorites(list)
        startActivity(Intent(requireContext(), FavoritesActivity::class.java))
    }

    override fun addToFavorites(favorites: BigBetter) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: SignaturePizza) {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(favorites: Appetizers) {
        TODO("Not yet implemented")
    }


    override fun onDetailsOnItemClicked(details: ValuesDeals) {
=======

    override fun onDetailsOnItemClicked(cart: ValuesDeals) {
>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(type, "details")
        intent.putExtra(imgUrl, details.imgUrl)
        intent.putExtra(size, details.size)
        intent.putExtra(price, details.price)
        startActivity(intent)
    }

<<<<<<< HEAD
    companion object {
        private const val price = "price"
        private const val size = "size"
        private const val imgUrl = "imgUrl"
        private const val type = "type"
    }
=======



    fun alertDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
        val button1 = view.findViewById<Button>(R.id.cancel)
        val button2 = view.findViewById<Button>(R.id.choose1)
        val button3 = view.findViewById<Button>(R.id.choose2)

        builder.setView(view)
        button1.setOnClickListener {
            builder.dismiss()
        }

        button2.setOnClickListener {
            alertDialog_a()
            view.isVisible = false
        }

        button3.setOnClickListener {
            alertDialog_b()
            view.isVisible = false
        }
        builder.setCanceledOnTouchOutside(true)
        builder.show()

    }

    private fun alertDialog_a() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view1 = layoutInflater.inflate(R.layout.second_alertdialog, null)
        val cancelCrust = view1.findViewById<Button>(R.id.cancel1)
        val confirmCrust = view1.findViewById<Button>(R.id.Confirm)

        cancelCrust.setOnClickListener {
            builder.dismiss()
        }

        builder.setView(view1)
        builder.setCanceledOnTouchOutside(true)
        builder.show()

        confirmCrust.setOnClickListener {
            returnDialog1()
            view1.isVisible = false

        }

    }


    private fun alertDialog_b() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view2 = layoutInflater.inflate(R.layout.third_alertdialog, null)
        val cancelFlavors = view2.findViewById<Button>(R.id.cancel2)
        val confirmFlavors = view2.findViewById<Button>(R.id.Confirm1)

        confirmFlavors.setOnClickListener {
            returnDialog2()
            view2.isVisible = false
        }

        cancelFlavors.setOnClickListener {
            builder.dismiss()
        }
        builder.setView(view2)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }


    private fun returnDialog1() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

    private fun returnDialog2() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()

    }



    override fun onFavoriteClick(valuesDeals: ValuesDeals) {
        val intent = Intent(requireContext(), FavoritesActivity::class.java)
        intent.putExtra("favoriteType", "Edit")
        intent.putExtra("favoriteTitle", valuesDeals.id)
        intent.putExtra("favoriteDescription", valuesDeals.size)
        intent.putExtra("favoriteImg", valuesDeals.imgUrl)
        intent.putExtra("favoritePrice", valuesDeals.price)
        startActivity(intent)
    }

>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
}