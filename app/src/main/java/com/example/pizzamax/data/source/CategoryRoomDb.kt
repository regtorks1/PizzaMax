package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzamax.data.dao.CategoryDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.model.Category
import com.example.pizzamax.model.CategoryItems
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Category::class, CategoryItems::class],
    version = 1, exportSchema = true
)

@TypeConverters(Converters::class)
 abstract class CategoryRoomDb : RoomDatabase() {
   abstract fun categoryDao() : CategoryDao
   abstract fun categoryItemsDao() : CategoryItemsDao

    companion object {
        @Volatile// Singleton prevents multiple instances of database opening at the same time
        var INSTANCE: CategoryRoomDb? = null
        fun getDatabase(context: Context, scope: CoroutineScope): CategoryRoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoryRoomDb::class.java,
                    "MaxDatabase"
                ).addCallback(CategoryDataCallback(context, scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}