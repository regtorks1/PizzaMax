package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzamax.data.dao.*
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoriesList
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Categories::class, CategoriesList::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun categoryListDao(): CategoryListDao

    companion object {
        @Volatile// Singleton prevents multiple instances of database opening at the same time
        var INSTANCE: RoomDb? = null
        fun getDatabase(context: Context, scope: CoroutineScope): RoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "PizzaMaxDb"
                ).allowMainThreadQueries()
                    .addCallback(ListDatabaseCallback(context, scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}