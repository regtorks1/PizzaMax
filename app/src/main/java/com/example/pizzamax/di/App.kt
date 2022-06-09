package com.example.pizzamax.di

import android.app.Application
import com.example.pizzamax.data.repository.ValueDealsRepository
import com.example.pizzamax.data.source.RoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts

    private val appScope = CoroutineScope(SupervisorJob())
    private val roomDatabaseInstance by lazy { RoomDb.getDatabase(this, appScope) }
    val repository by lazy { ValueDealsRepository(roomDatabaseInstance.dealsDao()) }
}