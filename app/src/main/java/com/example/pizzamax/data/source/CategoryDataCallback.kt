package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.CategoryDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.data.source.CategoryRoomDb.Companion.INSTANCE
import com.example.pizzamax.model.Category
import com.example.pizzamax.model.CategoryItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class CategoryDataCallback(
    private val application: Context,
    private val scope: CoroutineScope
) :
    RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateCategory(database.categoryDao())
                populateCategoryItems(database.categoryItemsDao())
            }

        }
    }

    private suspend fun populateCategory(categoryDao: CategoryDao) {
        val bufferReader = application.assets.open("product.json").bufferedReader()
        val jsonString = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val categoryName = jsonObject.getString("name")
            val size = jsonObject.getString("size")
            val price = jsonObject.getString("price")
            val imgUrl = jsonObject.getString("image")
            val categoryList = jsonObject.getJSONArray("categoryItems")
            val category = Category(
                id = id.toInt(), categoryName = categoryName, categoryList = mutableListOf(
                    CategoryItems(id = id.toInt(), imgUrl = imgUrl, size = size, price = price)
                )
            )
            categoryDao.insertToRoom(category)
        }
    }

    private suspend fun populateCategoryItems(categoryItemsDao: CategoryItemsDao) {
        val bufferReader = application.assets.open("product.json").bufferedReader()
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
            val categoryItems =
                CategoryItems(id = id.toInt(), size = size, price = price, imgUrl = imgUrl)
            categoryItemsDao.insertToRoom(categoryItems)
        }
    }

}