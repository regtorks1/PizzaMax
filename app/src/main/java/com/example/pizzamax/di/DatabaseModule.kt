package com.example.pizzamax.di

import android.content.Context
import androidx.room.Room
import com.example.pizzamax.data.dao.CartDao
import com.example.pizzamax.data.dao.CategoriesDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.data.dao.FavoritesDao
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.data.source.ALTER_TABLE_MIGRATION_1_2
import com.example.pizzamax.data.source.ListDatabaseCallback
import com.example.pizzamax.data.source.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Volatile// Singleton prevents multiple instances of database opening at the same time
    // if the INSTANCE is not null, then return it, if it is, then create the database
    var databaseInstance: RoomDb? = null

    //providing database daos instances to hilt
    @Provides
    @Singleton
    fun providesCategoriesDatabase(productDatabase: RoomDb): CategoriesDao =
        productDatabase.categoriesDao()

    @Provides
    @Singleton
    fun providesCategoryListDatabase(productDatabase: RoomDb): CategoryItemsDao =
        productDatabase.categoryItemsDao()

    @Provides
    @Singleton
    fun providesCartDatabase(productDatabase: RoomDb): CartDao = productDatabase.cartDao()

    @Provides
    @Singleton
    fun providesFavoriteDatabase(productDatabase: RoomDb): FavoritesDao =
        productDatabase.favorites()


    //providing product repository
    @Provides
    fun providesProductRepository(
        cartDao: CartDao,
        categoriesDao: CategoriesDao,
        favoritesDao: FavoritesDao,
        categoryItemsDao: CategoryItemsDao
    ): ProductRepository = ProductRepository(
        categoriesDao, categoryItemsDao, cartDao, favoritesDao
    )

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context, scope: CoroutineScope): RoomDb {
        return databaseInstance ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context, RoomDb::class.java, "PizzaMaxDatabase"
            ).allowMainThreadQueries()
                .addMigrations(ALTER_TABLE_MIGRATION_1_2)
                .addCallback(ListDatabaseCallback(context, scope))
                .build()
            databaseInstance = instance
            // return instance
            instance
        }
    }


}