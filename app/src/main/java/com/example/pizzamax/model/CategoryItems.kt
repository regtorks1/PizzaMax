package com.example.pizzamax.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "category_list")
data class CategoryItems(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "image") var imgUrl: String?=null,
    @ColumnInfo(name = "size") var size: String?=null,
    @ColumnInfo(name = "price") var price: String?=null

):Parcelable
