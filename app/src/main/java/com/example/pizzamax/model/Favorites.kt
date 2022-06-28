package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "favorites")
@Serializable
data class Favorites(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "image") val imgUrl: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "price") val price: String
)
