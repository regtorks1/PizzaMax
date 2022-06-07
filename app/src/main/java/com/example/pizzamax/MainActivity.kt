package com.example.pizzamax

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat.getActionView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pizzamax.databinding.ActivityMainBinding

import com.example.pizzamax.di.App
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.views.CheckoutActivity
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.util.getBitmap
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity(), ValuesDealRecyclerAdapter.UpdateCheckout {
    private lateinit var binding: ActivityMainBinding
    private val activityViewmodel: ActivityViewmodel by viewModels {
        ActivityViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerAdapter: ValuesDealRecyclerAdapter by lazy { ValuesDealRecyclerAdapter(this, this) }  //initialize adapter

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(this)


        activityViewmodel.getList.observe(this, Observer {
            lifecycleScope.launch {
<<<<<<< HEAD
                //activityViewmodel.deleteAll()
=======
              // activityViewmodel.deleteAll()
>>>>>>> 1eba932d494d9874af0d2b23a3be8aa8547ff9b8
                productList()
                recyclerAdapter.submitList(it)
            }
        })
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

    private fun adapterOnClick() {
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }


    private suspend fun productList() {

        val bufferReader = application.assets.open("value_deala.json").bufferedReader()
        val jsonString = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val size = jsonObject.getString("size")
            val price = jsonObject.getString("price")
            val imgUrl = jsonObject.getString("image")
            val image = getBitmap(this@MainActivity, imgUrl)
                //Glide.with(this).asBitmap().load(imgUrl)
           val deal = ValuesDeals(image = image, size = size, price = price, id = id.toInt())
<<<<<<< HEAD
           //activityViewmodel.insertIntoRoom(deal)
=======
         //  activityViewmodel.insertIntoRoom(deal)
>>>>>>> 1eba932d494d9874af0d2b23a3be8aa8547ff9b8
            Log.d("readArrayOfJsonObject", "image: $image  name: $price || version : $size  \n")
        }
    }

    override fun onAddCart(cart: ValuesDeals) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putExtra("type","cart")
        intent.putExtra("size",cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
        this.finish()
    }


}