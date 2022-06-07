package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ValuesDeals::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun dealsDao(): ValueDealsDao

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
                    "AppDatabase"
                ).addCallback(ListDatabaseCallback(context,scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}