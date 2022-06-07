package com.example.pizzamax.data.source

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.R
import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.data.source.RoomDb.Companion.INSTANCE
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.views.util.getBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class ListDatabaseCallback(
    private val context: Context,
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.dealsDao())
            }
        }
    }

    private suspend fun populateDatabase(listDao: ValueDealsDao) {
        try {
            val list = loadJsonData()
            if (list != null) {
                for (i in 0 until list.length()) {//read from 0 until length-1
                    val item = list.getJSONObject(i)//obtain the json object

                    //get item by name
                    val price = item.getString("price")
                    val size = item.getString("size")
                    val image = item.getString("image")
                    val imgUrl = getBitmap(context, image)//if loading from url

                    //load the data into entity
                    val data = ValuesDeals(
                        imgUrl = image,
                        size = size,
                        price = price
                    )

                    //using dao to insert data to the database
                    listDao.insertToRoom(data)

                }

            }
        } catch (e: JSONException) {
            Log.d("populateDatabase", "$e")
        }
    }

    private fun loadJsonData(): JSONArray {
        //obtain input from resources
        val inputStream = context.resources.openRawResource(R.raw.value_deala)

        //using buffered reader to read the inputStream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}
