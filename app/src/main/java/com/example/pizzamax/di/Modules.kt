package com.example.pizzamax.di

import android.app.Application
import androidx.room.Room
import com.example.pizzamax.data.dao.CartDao
import com.example.pizzamax.data.dao.CategoriesDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.data.dao.FavoritesDao
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.data.source.RoomDb
import com.example.pizzamax.viewmodel.ProductViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module{
    viewModel {
       ProductViewModel(get())
    }
}

val repositoryModule = module {
    fun provideUserRepository(
        cartDao: CartDao,
        categoriesDao: CategoriesDao,
        categoryItemsDao: CategoryItemsDao,
        favoritesDao: FavoritesDao
    ): ProductRepository {
      return ProductRepository(categoriesDao,categoryItemsDao,cartDao, favoritesDao)
    }

    single {
        provideUserRepository(get(), get(), get(), get())
    }
}

val roomDBModule = module {
    fun provideDatabase(application: Application) : RoomDb{
        return Room.databaseBuilder(application, RoomDb::class.java, "PizzaMaxDatabase")
            .allowMainThreadQueries()
            .build()
    }
    fun categoriesDao(database: RoomDb): CategoriesDao{
        return database.categoriesDao()
    }
    fun categoryItemsDao(database: RoomDb): CategoryItemsDao{
        return database.categoryItemsDao()
    }
    fun cartDao(database: RoomDb): CartDao{
        return database.cartDao()
    }
    fun favoritesDao(database: RoomDb): FavoritesDao{
        return database.favorites()
    }

    single {provideDatabase(androidApplication())}
    single {categoriesDao(get())}
    single {categoryItemsDao(get())}
    single {cartDao(get())}
    single {favoritesDao(get())}

}

