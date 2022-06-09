package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.*
import com.example.pizzamax.data.dao.AppetizersDao
import com.example.pizzamax.data.dao.BigBetterDoa
import com.example.pizzamax.data.dao.SignatureDao
import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.BigBetter
import com.example.pizzamax.model.SignaturePizza
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [ValuesDeals::class, BigBetter::class, Appetizers::class, SignaturePizza::class],
    version = 1,
    exportSchema = true)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun dealsDao(): ValueDealsDao
    abstract fun bigBetterDao(): BigBetterDoa
    abstract fun signatureDao(): SignatureDao
    abstract  fun appetizersDao(): AppetizersDao

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
                    "PizzaMaxDatabase"
                ).addCallback(ListDatabaseCallback(context,scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}