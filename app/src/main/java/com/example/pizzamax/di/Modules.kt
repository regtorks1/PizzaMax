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
import com.example.pizzamax.views.adapters.ProductListAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module


val viewModelModule = module{
    viewModel {
       ProductViewModel(repository = get())
    }
}

val repositoryModule = module{
    factory {
        ProductRepository(cartDao = )
    }
}

val roomDBModule = module {
    fun provideDatabase(application: Application) : RoomDb{
        return Room.databaseBuilder(application, RoomDb::class.java, "DATABASE")
            .fallbackToDestructiveMigration()
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

    single { provideDatabase(androidApplication()) }
    single {categoriesDao(get())}
    single {categoryItemsDao(get())}
    single {cartDao(get())}
    single {favoritesDao(get())}

}

