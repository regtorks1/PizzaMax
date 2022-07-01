package com.example.pizzamax.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems

data class CategoryAndItems(

    @Embedded val categories: Categories,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryItemsId"
    )
    val categoryItems: MutableList<CategoryItems>
)
