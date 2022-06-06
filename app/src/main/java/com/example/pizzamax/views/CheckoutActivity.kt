package com.example.pizzamax.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamax.R
import com.example.pizzamax.databinding.ActivityCheckoutBinding
import kotlin.math.absoluteValue


class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

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
       cartCalculator()


    }


  private  fun cartCalculator() {
        var incrementAmt = 1
        with(binding) {
            val amt = Integer.parseInt(price.text.toString())
            var incrementNum = Integer.parseInt(increment.text.toString())
            addBtn.setOnClickListener {
                incrementNum += 1
                incrementAmt = amt * incrementNum
                increment.text = incrementNum.toString()
                price.text = incrementAmt.toString()
                subTotal.text=price.text
                grandTotal1.text=price.text
            }

            negBtn.setOnClickListener {
                incrementNum -= 1
                incrementAmt = amt * incrementNum
                increment.text = incrementNum.toString()
                price.text = incrementAmt.toString()
                subTotal.text=price.text
                 grandTotal1.text=price.text
            }
        }

    }

    /*private fun subBtn() {
        var incrementAmt = 1
        with(binding) {
            val amt = Integer.parseInt(price.text.toString())
            var incrementNum = Integer.parseInt(increment.text.toString())
            negBtn.setOnClickListener {
                incrementNum -= 1
                incrementAmt = amt * incrementNum.absoluteValue
                increment.text = incrementNum.toString()
                price.text = incrementAmt.toString()
            }
        }

    }*/

}