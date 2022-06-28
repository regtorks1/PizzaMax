package com.example.pizzamax.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "expenses")
@Serializable
data class Expenses(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "quantity") val quantity: String,
    @ColumnInfo(name = "amount") val amount: String
)

