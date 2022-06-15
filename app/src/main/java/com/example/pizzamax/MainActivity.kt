package com.example.pizzamax

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.MenuItemCompat.getActionView
import androidx.fragment.app.viewModels
import com.example.pizzamax.databinding.ActivityMainBinding
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Favorites
import com.example.pizzamax.model.SliderData
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory
import com.example.pizzamax.views.adapters.SliderAdapter
import com.example.pizzamax.views.adapters.ViewPagerAdapter
import com.example.pizzamax.views.ui.activity.CheckoutActivity
import com.example.pizzamax.views.ui.activity.FavoritesActivity
import com.google.android.material.tabs.TabLayout
import com.smarteist.autoimageslider.SliderView


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

//    private val productViewmodel: ProductViewModel by viewModels {
//        ProductViewModelFactory((activity?.application as App).productRepository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(binding.root)

        binding.linearViewCart.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }



        imageSlider()
        setupTabLayout()
        setupViewPager()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                this.startActivity(Intent(this, FavoritesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, binding.tabLayout.tabCount)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        }
    }


    private fun setupTabLayout() {
        binding.tabLayout.apply {
            addTab(this.newTab().setText("Max Value Deals"))
            addTab(this.newTab().setText("2 Big 2 Better"))
            addTab(this.newTab().setText("Appetizers"))
            addTab(this.newTab().setText("Signature Pizza"))
            addTab(this.newTab().setText("Max Value Deals"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.viewPager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    private fun imageSlider() {
        val sliderImg = intArrayOf(
            R.drawable.pizza_max_poster,
            R.drawable.pizza_max_poster1,
            R.drawable.pizza_max_poster2,
            R.drawable.pizza_max_poster3
        )
        val imageView : SliderView = binding.slider
        imageView.fitsSystemWindows = true

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

    fun setUp(){
        Toast.makeText(this, "SOMETHING", Toast.LENGTH_SHORT)
    }

}