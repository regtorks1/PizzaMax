package com.example.pizzamax.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pizzamax.views.ui.fragments.AppetizersFragment
import com.example.pizzamax.views.ui.fragments.BigBetterFragment
import com.example.pizzamax.views.ui.fragments.SignaturePizzaFragment
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment

class ViewPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm, tabCount) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ValueDealsFragment()
            }
            1 -> {
                BigBetterFragment()
            }
            2 -> {
                AppetizersFragment()
            }
            3 -> {
                SignaturePizzaFragment()
            }
            else -> {
                ValueDealsFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Tab " + (position + 1)
    }


}