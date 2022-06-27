package com.example.pizzamax.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzamax.data.dao.*
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [
        Categories::class,
        CategoryItems::class,
        Cart::class, Favorites::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun categoryItemsDao(): CategoryItemsDao
    abstract fun cartDao():CartDao
    abstract fun favorites(): FavoritesDao
}