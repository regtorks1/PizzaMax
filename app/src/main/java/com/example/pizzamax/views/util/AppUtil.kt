package com.example.pizzamax.views.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.pizzamax.R

/**
 * This method convert image url to bitmap
 * @param context : get application context
 * @param imageUrl : image uri
 *
 * */

suspend fun getBitmap(context: Context, imageUrl: String): Bitmap {
    val loading = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .build()
    val result = (loading.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap

}


fun String.toInteger(string: String) = Integer.parseInt(string)

@SuppressLint("SetTextI18n")
fun Fragment.mainAlertDialog(title: String, price: String) {
    val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
    val view = layoutInflater.inflate(R.layout.fragment_main_alert, null)
    val cartView = layoutInflater.inflate(R.layout.activity_main, null)

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


    //initializing cart bottom items
    val items = cartView.findViewById<TextView>(R.id.item_number)
    val totalCartAmt = cartView.findViewById<TextView>(R.id.amount)




    //Calculation
    desc.text = title
    priceAlert.text = price
    total.text = price
    var adder = Integer.parseInt(increment.text.toString())
    val amt = Integer.parseInt(priceAlert.text.toString())

    plusBtn.setOnClickListener {
        adder +=1
        val totalAmt = adder.times(amt)
        increment.text = adder.toString()
        total.text = totalAmt.toString()

        items.text = "$adder items"
        totalCartAmt.text = "Ghc $totalAmt"
    }

    negBtn.setOnClickListener {
        adder -=1
        val totalAmt = adder.times(amt)
        increment.text = adder.toString()
        total.text = totalAmt.toString()
    }

    addToCartBtn.setOnClickListener {
        val openCart = cartView.findViewById<LinearLayout>(R.id.linear_view_cart)
         builder.dismiss()
         openCart.visibility = View.VISIBLE
    }









   /* val passIntent = Intent()
    passIntent.putExtra("price", price)
    activity?.setResult(Activity.RESULT_OK, passIntent)*/



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

 fun Fragment.crustAlertDialog() {
    val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
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

fun Fragment.alertDialog_b() {
    val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
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


fun Fragment.returnDialog1() {
    val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
    val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
    builder.setView(view)
    builder.setCanceledOnTouchOutside(true)
    builder.show()
}

fun Fragment.returnDialog2() {
    val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
    val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
    builder.setView(view)
    builder.setCanceledOnTouchOutside(true)
    builder.show()

}

