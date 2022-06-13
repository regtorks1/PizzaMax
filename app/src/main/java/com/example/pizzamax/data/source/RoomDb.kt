package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzamax.data.dao.*
import com.example.pizzamax.model.*
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [ValuesDeals::class, BigBetter::class, Appetizers::class, SignaturePizza::class, Cart::class, Favorites::class, Expenses::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun dealsDao(): ValueDealsDao
    abstract fun bigBetterDao(): BigBetterDoa
    abstract fun signatureDao(): SignatureDao
    abstract fun appetizersDao(): AppetizersDao
    abstract fun favoritesDao(): FavoritesDao
    abstract fun cartDao(): CartDao
    abstract fun expensesDao(): ExpensesDao

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
                    "MaxDatabase"
                ).addCallback(ListDatabaseCallback(context, scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}