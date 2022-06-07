package com.example.pizzamax.views.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import org.json.JSONArray

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
