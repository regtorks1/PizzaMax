package com.example.pizzamax

import android.content.Intent
import android.os.Bundle
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
import com.example.pizzamax.databinding.ActivityMainBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.viewmodel.ActivityViewModelFactory
import com.example.pizzamax.viewmodel.ActivityViewmodel
import com.example.pizzamax.views.CheckoutActivity
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityViewmodel: ActivityViewmodel by viewModels {
        ActivityViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerAdapter: ValuesDealRecyclerAdapter by lazy { ValuesDealRecyclerAdapter() }  //initialize adapter

        //recycler setup
        val thisRecycler = binding.recyclerView
        thisRecycler.adapter = recyclerAdapter
        thisRecycler.layoutManager = LinearLayoutManager(this)

        activityViewmodel.getList.observe(this, Observer {
            lifecycleScope.launch {
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


    /*  suspend fun productList() {

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
              val deal = ValuesDeals(imgUrl = imgUrl, size = size, price = price, id = id.toInt())
             activityViewmodel.insertIntoRoom(deal)
              Log.d("readArrayOfJsonObject", "image: $imgUrl  name: $price || version : $size  \n")
          }
      }*/


}