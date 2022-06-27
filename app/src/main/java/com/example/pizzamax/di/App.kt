package com.example.pizzamax.di

import android.app.Application
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.data.source.RoomDb
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application() {}