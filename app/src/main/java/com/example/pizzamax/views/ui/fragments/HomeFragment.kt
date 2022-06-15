package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentHomeBinding
import com.example.pizzamax.model.SliderData
import com.example.pizzamax.views.adapters.SliderAdapter
import com.example.pizzamax.views.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.smarteist.autoimageslider.SliderView


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setupTabLayout()
        setupViewPager()
        imageSlider()
        return binding.root
    }

      private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = ViewPagerAdapter(childFragmentManager, binding.tabLayout.tabCount)
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

        val adapter = SliderAdapter(requireContext(), sliderDataArrayList)
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        sliderView.setSliderAdapter(adapter)
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()
    }
}