package com.example.pizzamax.data.source

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.CategoriesDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.di.DatabaseModule
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems
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
    @Volatile
    private var databaseInstance: RoomDb? =null
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        DatabaseModule.databaseInstance.let { roomDb ->
            scope.launch {
                populateProductTable(
                    roomDb!!.categoriesDao()
                )
            }
        }
    }

    private fun populateProductTable(
        categoriesDao: CategoriesDao
    ) {
        val jsonFile = getJsonDataFromAsset(application, "product_list.json")
        val jsonObj = JSONObject(jsonFile!!)
        val jsonArray: JSONArray = jsonObj.getJSONArray("categories")

        //getting the category names with their corresponding list
        for (i in 0 until jsonArray.length()) {
            val categoryItems = mutableListOf<CategoryItems>()
            val name = jsonArray.getJSONObject(i).getString("name")
            val list = jsonArray.getJSONObject(i).getJSONArray("items")
            for (j in 0 until list.length()) {
                val id = list.getJSONObject(j).getString(id)
                val size = list.getJSONObject(j).getString(size)
                val price = list.getJSONObject(j).getString(price)
                val imgUrl = list.getJSONObject(j).getString(imgUrl)
                categoryItems.add(
                    CategoryItems(
                        id = id.toInt(),
                        size = size,
                        price = price,
                        imgUrl = imgUrl
                    )
                )
            }//getting the items from each category name

            //inserting into database
            val categories = mutableListOf<Categories>()
                categories.add(Categories(id = i, name = name, categoryItems))
                categoriesDao.insertToCategories(categories)
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
