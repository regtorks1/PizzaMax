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

        val recyclerAdapter: ValuesDealRecyclerAdapter by lazy { ValuesDealRecyclerAdapter(this, this) }  //initialize adapter

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


//    private fun addToRoom() = lifecycleScope.launch {
//        val image = getBitmap(
//            this@MainActivity,
//            "https://img.freepik.com/free-photo/top-view-pepperoni-pizza-with-mushroom-sausages-bell-pepper-olive-corn-black-wooden_141793-2158.jpg?size=626&ext=jpg&ga=GA1.2.707152998.1654271208"
//        )
//        val deal = ValuesDeals(image = image, size = 1, price = "$100")
//        activityViewmodel.insertIntoRoom(deal)
//    }

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


    override fun onAddCart(cart: ValuesDeals) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putExtra("type","cart")
        intent.putExtra("size",cart.size)
        intent.putExtra("price", cart.price)
        startActivity(intent)
        this.finish()
    }


}