package com.example.pizzamax.data.source

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.CategoriesDao
import com.example.pizzamax.data.dao.CategoryListDao
import com.example.pizzamax.data.source.RoomDb.Companion.INSTANCE
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoriesList
import com.example.pizzamax.views.util.getJsonDataFromAsset
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
                populateProductTable(
                    database.categoriesDao(),
                    database.categoryListDao()
                )
            }
        }
    }


    private fun populateProductTable(
        categoriesDao: CategoriesDao,
        categoryListDao: CategoryListDao
    ) {
        val jsonFile = getJsonDataFromAsset(application, "product_list.json")
        Log.d("DATA", jsonFile.toString())
        val jsonObj = JSONObject(jsonFile!!)
        val jsonArray: JSONArray = jsonObj.getJSONArray("categories")
        Log.d("CATEGORIES", "\n$jsonArray")
        var categoriesList = CategoriesList()

        for (i in 0 until jsonArray.length()) {
            val name = jsonArray.getJSONObject(i).getString("name")
            val list = jsonArray.getJSONObject(i).getJSONArray("items")
            Log.d("categories", "\n$name \n$list")
            Log.d("LENGTH", "${list.length()}")
            for (j in 0 until list.length()) {
                val id = list.getJSONObject(j).getString(id)
                val size = list.getJSONObject(j).getString(size)
                val price = list.getJSONObject(j).getString(price)
                val imgUrl = list.getJSONObject(j).getString(imgUrl)
                categoriesList =
                    CategoriesList(id = id.toInt(), size = size, price = price, imgUrl = imgUrl)
                categoryListDao.insertToCategoryList(categoriesList)
            }

            val categories = Categories(id = i, name = name, listOf(categoriesList))
            categoriesDao.insertToCategories(categories)
            Log.d("categories", ":::::::::::::::$categories")

            Log.d("ListItems", ":::::::::::::::$categoriesList")

        }
    }


    companion object {
        const val id = "id"
        const val size = "size"
        const val price = "price"
        const val imgUrl = "image"

        const val product = "product_list"
        const val categories = "categories"
        const val name = "name"
        const val items = "items"

    }
}
