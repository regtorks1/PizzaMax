package com.example.pizzamax.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems

data class CategoryWithCategoriesItems(
    @Embedded val categories: Categories,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryItemsId"
    ) var categoryItems: MutableList<CategoryItems>
)
