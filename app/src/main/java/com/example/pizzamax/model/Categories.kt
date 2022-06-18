package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories")
data class Categories(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "list") val list: List<CategoriesList>
)
