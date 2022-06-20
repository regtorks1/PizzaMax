package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class ProductList (@PrimaryKey(autoGenerate = true) var id : Int = 0,
                        @ColumnInfo(name = "categoryName") var categoryName : String,
                        @ColumnInfo(name = "image") val imgUrl: String,
                        @ColumnInfo(name = "size") val size: String,
                        @ColumnInfo(name = "price") val price: String
)
