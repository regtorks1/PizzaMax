package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "big_better")
data class BigBetter(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "image") var imgUrl: String?=null,
    @ColumnInfo(name = "size") var size: String?=null,
    @ColumnInfo(name = "price") var price: String?=null
)
