package com.example.pizzamax.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "deals_table")
data class ValuesDeals(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "image") var imgUrl: String? =null,
    @ColumnInfo(name = "size") var size: String?=null,
    @ColumnInfo(name = "price") var price: String? = null
)

