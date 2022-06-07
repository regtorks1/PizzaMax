package com.example.pizzamax.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "deal")
data class ValuesDeals(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "image") val image: Bitmap,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "price") val price: String
)

