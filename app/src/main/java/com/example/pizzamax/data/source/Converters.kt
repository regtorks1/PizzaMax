package com.example.pizzamax.data.source

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.room.TypeConverter
import com.example.pizzamax.model.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import java.util.*

class Converters {

    @TypeConverter
    fun listToJson(list : String?): List<CategoryItems?>?{
        if (list ==null) {
            Log.d("@TypeConverters","$list is empty")
            return Collections.emptyList()
        }
        val listType: Type = object:TypeToken<List<CategoryItems?>?>(){}.type
        return Gson().fromJson<List<CategoryItems?>>(list, listType)
    }

    @TypeConverter
    fun jsonToString(list: List<CategoryItems?>?):String{
        return Gson().toJson(list)
    }

    /**
     * Tell room that whenever it sees bitmap it should convert it to bytArray
     **/
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)// format
        return outputStream.toByteArray()
    }

    /**convert bit array to bitmap*/
    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}