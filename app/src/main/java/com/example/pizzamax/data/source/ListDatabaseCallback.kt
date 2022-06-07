package com.example.pizzamax.data.source

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.data.source.RoomDb.Companion.INSTANCE
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ListDatabaseCallback(
    private val application: Context,
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateList(database.dealsDao())
            }
        }
    }


    private suspend fun populateList(valueDealsDao: ValueDealsDao) {
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
            val deal = ValuesDeals(imgUrl = imgUrl, size = size, price = price, id = id.toInt())
            valueDealsDao.insertToRoom(deal)
            Log.d("readArrayOfJsonObject", "image: $imgUrl  name: $price || version : $size  \n")
        }
    }
}
