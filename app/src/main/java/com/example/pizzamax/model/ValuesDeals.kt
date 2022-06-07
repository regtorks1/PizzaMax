package com.example.pizzamax.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "deals_table")
data class ValuesDeals(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "image") val imgUrl: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "price") val price: String
)

