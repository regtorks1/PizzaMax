package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "category_list",
    foreignKeys = [ForeignKey(
        entity =
        Categories::class,
        childColumns = arrayOf("catId"),
        parentColumns = arrayOf("categoryId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class CategoryItems(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "items_id") var items_id: Int = 0,
    @ColumnInfo(name = "catId") var catId: Int,
    @ColumnInfo(name = "image") var imgUrl: String? = null,
    @ColumnInfo(name = "size") var size: String? = null,
    @ColumnInfo(name = "price") var price: String? = null

)
