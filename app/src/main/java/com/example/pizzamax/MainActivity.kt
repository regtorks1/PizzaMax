package com.example.pizzamax

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat.getActionView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.databinding.ActivityMainBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.SliderData
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.views.CheckoutActivity
import com.example.pizzamax.views.adapters.SliderAdapter
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.launch
import java.util.ArrayList


class MainActivity : AppCompatActivity(), ValuesDealRecyclerAdapter.UpdateCheckout {
    private lateinit var binding: ActivityMainBinding
    private val activityViewmodel: ActivityViewmodel by viewModels {
        ActivityViewModelFactory((application as App).repository)
    }

    private var sliderImg = intArrayOf(
        R.drawable.pizza_max_poster,
        R.drawable.pizza_max_poster1,
        R.drawable.pizza_max_poster2,
        R.drawable.pizza_max_poster3
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerAdapter: ValuesDealRecyclerAdapter by lazy {
            ValuesDealRecyclerAdapter(
                this,
                this,
                this
            )
        }  //initialize adapter

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(this)


        activityViewmodel.getList.observe(this, Observer {
            lifecycleScope.launch {
                //activityViewmodel.deleteAll()
                // productList()

                recyclerAdapter.submitList(it)
            }
        })

        imageSlider()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)
        val item: MenuItem = menu.findItem(R.id.spinner)
        val spinner: Spinner = getActionView(item) as Spinner

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_list_item_array, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        return true
    }


    override fun onAddCart(cart: ValuesDeals) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putExtra("type", "cart")
        intent.putExtra("size", cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
        this.finish()
    }

    private fun imageSlider() {
        val sliderDataArrayList = ArrayList<SliderData>()
        val sliderView: SliderView = binding.slider
        sliderDataArrayList.add(SliderData(sliderImg[0]))
        sliderDataArrayList.add(SliderData(sliderImg[1]))
        sliderDataArrayList.add(SliderData(sliderImg[2]))
        sliderDataArrayList.add(SliderData(sliderImg[3]))

        val adapter = SliderAdapter(this, sliderDataArrayList)
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        sliderView.setSliderAdapter(adapter)
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()
    }

    fun alertDialog() {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
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

     fun alertDialog_a(){
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
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


    fun returnDialog1(){
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
        Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }


    fun alertDialog_b(){
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
        val view2 = layoutInflater.inflate(R.layout.third_alertdialog, null)
        val cancelFlavors = view2.findViewById<Button>(R.id.cancel2)

        cancelFlavors.setOnClickListener {
            builder.dismiss()
        }
        builder.setView(view2)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }


}