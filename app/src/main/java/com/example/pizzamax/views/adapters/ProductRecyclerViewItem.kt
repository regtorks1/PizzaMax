package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class ProductRecyclerViewItem {
    @Entity(tableName = "favorites")
    data class Favorites(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "image") val imgUrl: String,
        @ColumnInfo(name = "size") val size: String,
        @ColumnInfo(name = "price") val price: String
    ) : ProductRecyclerViewItem()

    @Entity(tableName = "appetizers")
    data class Appetizers(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "image") var imgUrl: String? = null,
        @ColumnInfo(name = "size") var size: String? = null,
        @ColumnInfo(name = "price") var price: String? = null
    ) : ProductRecyclerViewItem()

    @Entity(tableName = "big_better")
    data class BigBetter(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "image") var imgUrl: String? = null,
        @ColumnInfo(name = "size") var size: String? = null,
        @ColumnInfo(name = "price") var price: String? = null
    ) : ProductRecyclerViewItem()

    @Entity(tableName = "cart")
    data class Cart(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "title") val itemName: String,
        @ColumnInfo(name = "size") val pizzaSize: String,
        @ColumnInfo(name = "price") val price: String,
        @ColumnInfo(name = "crust") val crust: String,
        @ColumnInfo(name = "flavors") val flavors: String,
        @ColumnInfo(name = "quantity") val quantity: String,
    ) : ProductRecyclerViewItem()

    @Entity(tableName = "expenses")
    data class Expenses(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "quantity") val quantity: String,
        @ColumnInfo(name = "amount") val amount: String
    ) : ProductRecyclerViewItem()




    @Entity(tableName = "signature")
    data class SignaturePizza(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "image") val imgUrl: String,
        @ColumnInfo(name = "size") val size: String,
        @ColumnInfo(name = "price") val price: String
    ) : ProductRecyclerViewItem()

    @Entity(tableName = "deals_table")
    data class ValuesDeals(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "image") var imgUrl: String? = null,
        @ColumnInfo(name = "size") var size: String? = null,
        @ColumnInfo(name = "price") var price: String? = null
    ) : ProductRecyclerViewItem()
}
