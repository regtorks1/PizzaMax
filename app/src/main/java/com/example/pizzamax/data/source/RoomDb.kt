package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzamax.data.dao.*
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [ProductRecyclerViewItem.ValuesDeals::class, ProductRecyclerViewItem.BigBetter::class, ProductRecyclerViewItem.Appetizers::class, ProductRecyclerViewItem.SignaturePizza::class, ProductRecyclerViewItem.Cart::class, ProductRecyclerViewItem.Favorites::class, ProductRecyclerViewItem.Expenses::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun dealsDao(): ProductListDao.ValueDealsDao
    abstract fun bigBetterDao(): ProductListDao.BigBetterDoa
    abstract fun signatureDao(): ProductListDao.SignatureDao
    abstract fun appetizersDao(): ProductListDao.AppetizersDao
    abstract fun favoritesDao(): ProductListDao.FavoritesDao
    abstract fun cartDao(): ProductListDao.CartDao
    abstract fun expensesDao(): ProductListDao.ExpensesDao

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
                    "MaxPDatabase"
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