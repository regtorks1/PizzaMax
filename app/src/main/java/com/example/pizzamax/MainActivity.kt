package com.example.pizzamax

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat.getActionView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzamax.databinding.ActivityMainBinding
import com.example.pizzamax.model.SliderData
import com.example.pizzamax.views.adapters.SliderAdapter
import com.example.pizzamax.views.adapters.ValuesDealRecyclerAdapter
import com.example.pizzamax.views.adapters.ViewPagerAdapter
import com.example.pizzamax.views.ui.CheckoutActivity
import com.example.pizzamax.views.ui.fragments.CustomDialog
import com.google.android.material.tabs.TabLayout
import com.smarteist.autoimageslider.SliderView


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextView.setOnClickListener{
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }


        imageSlider()
        setupTabLayout()
        setupViewPager()
        dialog()

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

    private fun dialog(){
        binding.customView.AddToCart.setOnClickListener {
            CustomDialog.newInstance("","","").show(supportFragmentManager, CustomDialog.TAG)
        }
    }

}