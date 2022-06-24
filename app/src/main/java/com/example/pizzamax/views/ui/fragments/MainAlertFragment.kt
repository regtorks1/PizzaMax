package com.example.pizzamax.views.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.pizzamax.R
import com.example.pizzamax.databinding.FragmentMainAlertBinding
import com.example.pizzamax.views.util.alertDialog_b
import com.example.pizzamax.views.util.crustAlertDialog

@Suppress("DEPRECATION")
class MainAlertFragment : Fragment() {
    private lateinit var binding: FragmentMainAlertBinding
   // private val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainAlertBinding.inflate(layoutInflater)
        return binding.root
    }

  /*  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE && requestCode == Activity.RESULT_OK) {
            data?.getStringExtra("price")?.let { price ->
                binding.totalPriceAlert.text = price
            }
        } else {
            Toast.makeText(
                activity,
                "price not saved",
                Toast.LENGTH_LONG
            ).show()
        }

    }*/

   /* fun alertDialog() {
        builder.setView(view)
        binding.cancel.setOnClickListener {
            builder.dismiss()
        }

        binding.choose1.setOnClickListener {
            crustAlertDialog()
            view?.isVisible = false
        }

        binding.choose2.setOnClickListener {
            alertDialog_b()
            view?.isVisible = false
        }
        builder.setCanceledOnTouchOutside(true)
        builder.show()

    }*/

}