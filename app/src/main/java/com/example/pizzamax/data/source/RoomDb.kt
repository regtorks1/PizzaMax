package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ValuesDeals::class], version = 1, exportSchema = false)
abstract class RoomDb : RoomDatabase() {
    abstract fun dealsDao(): ValueDealsDao

    class ListDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    //populate the table
                    val valuesDeals = database.dealsDao()
                    //delete the database
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null
        fun getDatabase(context: Context, scope: CoroutineScope): RoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "deals"
                ).addCallback(ListDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}