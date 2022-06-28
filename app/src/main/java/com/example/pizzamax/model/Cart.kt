package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "cart")
@Serializable
data class Cart(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") val itemName: String,
    @ColumnInfo(name = "size") val pizzaSize: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "crust") val crust: String,
    @ColumnInfo(name = "flavors") val flavors: String,
    @ColumnInfo(name = "quantity") val quantity: String,


)
