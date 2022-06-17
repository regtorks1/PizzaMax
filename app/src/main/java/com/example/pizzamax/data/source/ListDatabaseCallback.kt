package com.example.pizzamax.data.source

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.*
import com.example.pizzamax.data.source.RoomDb.Companion.INSTANCE
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@Suppress("BlockingMethodInNonBlockingContext")
class ListDatabaseCallback(
    private val application: Context,
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateDeals(database.dealsDao())
                populateBigBetter(database.bigBetterDao())
                populateAppetizers(database.appetizersDao())
                populateSignature(database.signatureDao())
            }
        }
    }

    private suspend fun populateDeals(valueDealsDao: ProductListDao.ValueDealsDao) {
        val bufferReader = application.assets.open("value_deala.json").bufferedReader()
        val jsonString = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val size = jsonObject.getString("size")
            val price = jsonObject.getString("price")
            val imgUrl = jsonObject.getString("image")
            val deal = ProductRecyclerViewItem.ValuesDeals(imgUrl = imgUrl, size = size, price = price, id = id.toInt())
            valueDealsDao.insertToRoom(deal)
            Log.d("readArrayOfJsonObject", "image: $imgUrl  name: $price || version : $size  \n")
        }
    }

     private suspend fun populateBigBetter(bigBetterDao: ProductListDao.BigBetterDoa) {
        val bufferReader = application.assets.open("2_big_2_better.json").bufferedReader()
        val jsonString = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val size = jsonObject.getString("size")
            val price = jsonObject.getString("price")
            val imgUrl = jsonObject.getString("image")
            val deal = ProductRecyclerViewItem.BigBetter(imgUrl = imgUrl, size = size, price = price, id = id.toInt())
            bigBetterDao.insertToRoom(deal)
            Log.d("readArrayOfJsonObject", "image: $imgUrl  name: $price || version : $size  \n")
        }
    }

        private suspend fun populateAppetizers(appetizersDao: ProductListDao.AppetizersDao) {
        val bufferReader = application.assets.open("appetizers.json").bufferedReader()
        val jsonString = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val size = jsonObject.getString("size")
            val price = jsonObject.getString("price")
            val imgUrl = jsonObject.getString("image")
            val deal = ProductRecyclerViewItem.Appetizers(imgUrl = imgUrl, size = size, price = price, id = id.toInt())
            appetizersDao.insertToRoom(deal)
            Log.d("readArrayOfJsonObject", "image: $imgUrl  name: $price || version : $size  \n")
        }
    }

        private suspend fun populateSignature(signatureDao: ProductListDao.SignatureDao) {
        val bufferReader = application.assets.open("signature.json").bufferedReader()
        val jsonString = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val size = jsonObject.getString("size")
            val price = jsonObject.getString("price")
            val imgUrl = jsonObject.getString("image")
            val deal = ProductRecyclerViewItem.SignaturePizza(imgUrl = imgUrl, size = size, price = price, id = id.toInt())
            signatureDao.insertToRoom(deal)
            Log.d("readArrayOfJsonObject", "image: $imgUrl  name: $price || version : $size  \n")
        }
    }
}
