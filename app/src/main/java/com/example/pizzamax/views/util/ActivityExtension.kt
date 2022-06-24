package com.example.pizzamax.views.util

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pizzamax.R
import com.example.pizzamax.di.App
import com.example.pizzamax.model.Cart
import com.example.pizzamax.viewmodel.ProductViewModel
import com.example.pizzamax.viewmodel.ProductViewModelFactory

@SuppressLint("SetTextI18n")
fun Activity.mainAlertDialog(
    title: String, price: String,
    itemClickListener: () -> Unit
) {
    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
    val view = layoutInflater.inflate(R.layout.fragment_main_alert, null)

    /*val productViewmodel: ProductViewModel by viewModels {
        ProductViewModelFactory((application as App).productRepository)
    }*/

    //initializing the custom views
    val button1 = view.findViewById<Button>(R.id.cancel)
    val button2 = view.findViewById<Button>(R.id.choose1)
    val button3 = view.findViewById<Button>(R.id.choose2)
    val plusBtn = view.findViewById<ImageView>(R.id.add_btn_alert)
    val negBtn = view.findViewById<ImageView>(R.id.neg_btn_alert)
    val addToCartBtn = view.findViewById<Button>(R.id.Add_to_Cart)
    val increment = view.findViewById<TextView>(R.id.increment_alert)
    val priceAlert = view.findViewById<TextView>(R.id.price_alert)
    val total = view.findViewById<TextView>(R.id.total_price_alert)
    val desc = view.findViewById<TextView>(R.id.deal_description)


    //Calculation
    desc.text = title
    priceAlert.text = price
    total.text = price
    var adder = Integer.parseInt(increment.text.toString())
    val amt = Integer.parseInt(priceAlert.text.toString())

    plusBtn.setOnClickListener {
        adder += 1
        val totalAmt = adder.times(amt)
        increment.text = adder.toString()
        total.text = totalAmt.toString()

    }

    negBtn.setOnClickListener {
        adder -= 1
        val totalAmt = adder.times(amt)
        increment.text = adder.toString()
        total.text = totalAmt.toString()
    }

    addToCartBtn.setOnClickListener {
        itemClickListener()
        builder.dismiss()
    }

    addToCartBtn.setOnClickListener {
        val totalAmt = adder.times(amt)
        val list = listOf(
            Cart(
                itemName = title,
                price = price,
                quantity = adder.toString(),
                pizzaSize = title,
                crust = "",
                flavors = "",
                quantityPrice = totalAmt.toString()
            )
        )

        Log.d("CART", ":::::::::::::${list}")
        // Log.d("DATABASE","::::::::${productViewmodel.insertIntoCart(list)}")
        itemClickListener()
        builder.dismiss()
    }



    builder.setView(view)
    button1.setOnClickListener {
        builder.dismiss()
    }

    button2.setOnClickListener {
        crustAlertDialog()
        view.isVisible = false
    }

    button3.setOnClickListener {
        alertDialog_b()
        view.isVisible = false
    }
    builder.setCanceledOnTouchOutside(true)
    builder.show()

}

fun Activity.crustAlertDialog() {
    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
    val view1 = layoutInflater.inflate(R.layout.second_alertdialog, null)
    val cancelCrust = view1.findViewById<Button>(R.id.cancel1)
    val confirmCrust = view1.findViewById<Button>(R.id.Confirm)

    cancelCrust.setOnClickListener {
        builder.dismiss()
    }

    builder.setView(view1)
    builder.setCanceledOnTouchOutside(true)
    builder.show()

    confirmCrust.setOnClickListener {
        returnDialog1()
        view1.isVisible = false

    }

}

fun Activity.alertDialog_b() {
    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
    val view2 = layoutInflater.inflate(R.layout.third_alertdialog, null)
    val cancelFlavors = view2.findViewById<Button>(R.id.cancel2)
    val confirmFlavors = view2.findViewById<Button>(R.id.Confirm1)

    confirmFlavors.setOnClickListener {
        returnDialog2()
        view2.isVisible = false
    }

    cancelFlavors.setOnClickListener {
        builder.dismiss()
    }
    builder.setView(view2)
    builder.setCanceledOnTouchOutside(true)
    builder.show()
}


fun Activity.returnDialog1() {
    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
    val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
    builder.setView(view)
    builder.setCanceledOnTouchOutside(true)
    builder.show()
}

fun Activity.returnDialog2() {
    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
    val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
    builder.setView(view)
    builder.setCanceledOnTouchOutside(true)
    builder.show()

}