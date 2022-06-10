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
import com.example.pizzamax.views.adapters.FavoriteClickInterface
import com.example.pizzamax.views.adapters.FavoritesAdapter
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.ui.CheckoutActivity
<<<<<<< HEAD
import com.example.pizzamax.views.ui.FavoritesActivity
=======
import com.example.pizzamax.views.util.alertDialog_b
import com.example.pizzamax.views.util.returnDialog1
>>>>>>> f29d83f800298ce357377459f542a272c20ea088
import kotlinx.coroutines.launch

class ValueDealsFragment : Fragment(), ValuesDealRecyclerAdapter.UpdateCheckout,  ValuesDealRecyclerAdapter.FavoriteClickInterface {
    private val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as App).productRepository)
    }

    companion object {
        fun newInstance() = ValueDealsFragment()
    }

    private lateinit var binding: FragmentValueDealsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValueDealsBinding.inflate(layoutInflater)

        val recyclerAdapter: ValuesDealRecyclerAdapter by lazy {
<<<<<<< HEAD
            ValuesDealRecyclerAdapter(this,this,this)
=======
            ValuesDealRecyclerAdapter(requireContext(),this, this)
>>>>>>> f29d83f800298ce357377459f542a272c20ea088
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
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
    }

<<<<<<< HEAD



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



=======
>>>>>>> f29d83f800298ce357377459f542a272c20ea088
}