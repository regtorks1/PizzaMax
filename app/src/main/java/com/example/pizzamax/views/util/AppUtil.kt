package com.example.pizzamax.views.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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

fun Fragment.mainAlertDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.first_alertdialog, null)
        val button1 = view.findViewById<Button>(R.id.cancel)
        val button2 = view.findViewById<Button>(R.id.choose1)
        val button3 = view.findViewById<Button>(R.id.choose2)

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

    private fun Fragment.crustAlertDialog() {
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

