package com.example.pizzamax.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") val itemName: String,
    @ColumnInfo(name = "size") val pizzaSize: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "crust") val crust: String,
    @ColumnInfo(name = "flavors") val flavors: String,
    @ColumnInfo(name = "quantity") val quantity: String,
    @ColumnInfo(name = "quantity_price") val quantityPrice: String
):Parcelable
