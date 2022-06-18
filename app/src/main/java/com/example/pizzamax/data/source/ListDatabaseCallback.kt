package com.example.pizzamax.data.source

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.*
import com.example.pizzamax.data.source.RoomDb.Companion.INSTANCE
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoriesList
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
                populateProductTable(
                    database.categoriesDao(),
                    database.categoryListDao()
                )
            }
        }
    }


    private suspend fun populateProductTable(
        categoriesDao: CategoriesDao,
        categoryListDao: CategoryListDao
    ) {
        val productReader = application.assets.open(product).bufferedReader()
        val readText = productReader.use {
            it.readText()
        }

        val jsonObj = JSONObject(readText)
        val jsonArr: JSONArray = jsonObj.getJSONArray(categories)
        for (i in 0 until jsonObj.length()) {
            val name = jsonArr.getJSONObject(i).getString(name)
            val items = jsonArr.getJSONObject(i).getJSONArray(items)

            val id = items.getJSONObject(i).getString(id)
            val size = items.getJSONObject(i).getString(size)
            val price = items.getJSONObject(i).getString(price)
            val imgUrl = items.getJSONObject(i).getString(imgUrl)

            val categoriesList = CategoriesList(
                id = id.toInt(),
                size = size,
                price = price,
                imgUrl = imgUrl
            )

            val categories = Categories(
                id = i,
                name = name,
                listOf(categoriesList)
            )
            //Insert into database
            categoriesDao.insertToCategories(categories)
            categoryListDao.insertToCategoryList(categoriesList)

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
