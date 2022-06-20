package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "categoryName") var categoryName : String,
    @ColumnInfo(name = "categoryList") var categoryList : List<CategoryItems>
)
