package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories")
data class Categories(
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "categoryId")var categoryId: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
  //  @ColumnInfo(name="items") var items: Int? = null
)
