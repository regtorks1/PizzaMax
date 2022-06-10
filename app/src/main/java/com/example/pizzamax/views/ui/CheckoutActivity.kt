package com.example.pizzamax.views.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamax.MainActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.ActivityCheckoutBinding


class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Spinner
        val firstSpinner: Spinner = binding.spinnerSelectPayment
        ArrayAdapter.createFromResource(
            this,
            R.array.payment_method,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            firstSpinner.adapter = adapter
        }

        val secondSpinner: Spinner = binding.spinnerSelectTime
        ArrayAdapter.createFromResource(
            this,
            R.array.time_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            secondSpinner.adapter = adapter
        }
        val type = intent.getStringExtra("type")
        if (type.equals("cart")) {
            val size = intent.getStringExtra("size")
            val priceString = intent.getStringExtra("price")

            with(binding) {
                price.text = priceString.toString()
                description.text = size.toString()
                amount.text = "*Ghc " + priceString.toString()
                subTotal.text = priceString.toString()
                grandTotal1.text = priceString.toString()
            }
        }

        cartCalculator()
        binding.arrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.delete.setOnClickListener {
            binding.subTotal.text = "0"
            binding.grandTotal1.text = "0"
            Toast.makeText(applicationContext, "Deleting cart items", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cartCalculator() {
        var incrementAmt = 1
        val negNum = -1
        with(binding) {
            try {
                val amt = Integer.parseInt(price.text.toString())
                var incrementNum = Integer.parseInt(increment.text.toString())
                if (incrementNum >= 1) {
                    addBtn.setOnClickListener {
                        incrementNum += 1
                        incrementAmt = amt * incrementNum
                        increment.text = incrementNum.toString()
                        price.text = incrementAmt.toString()
                        subTotal.text = price.text
                        grandTotal1.text = price.text
                    }
                    negBtn.setOnClickListener {
                        incrementNum -= 1
                        incrementAmt = amt * incrementNum
                        increment.text = incrementNum.toString()
                        price.text = incrementAmt.toString()
                        subTotal.text = price.text
                        grandTotal1.text = price.text
                    }
                } else {
                    Log.d("Calculation Err", "No matching value")

                    negBtn.setOnClickListener {
                        incrementNum -= 1
                        incrementAmt = amt * incrementNum
//                    if(incrementAmt <= 0) {
//                        negBtn.isEnabled = false
//                    }
                        increment.text = incrementNum.toString()
                        price.text = incrementAmt.toString()
                        subTotal.text = price.text
                        grandTotal1.text = price.text
                    }
                }

            } catch (e: NumberFormatException) {
                Log.d("NUMBER EXCEPTION", e.message.toString())
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}

