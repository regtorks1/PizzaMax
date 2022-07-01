package com.example.pizzamax.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems


@Dao
interface ProductListDao {

    @Insert
     fun addNewCategory(categories: MutableList<Categories>)

    @Insert
    fun addNewItems(categoryItems: List<CategoryItems>)

    @Transaction
    fun addNewCategoryWithItems(categories: MutableList<Categories>, categoryItems: List<CategoryItems>){
        val listId = addNewCategory(categories)
       // categoryItems.forEach {it.categoryId = listId.toInt() }
        addNewCategory(categories)
        addNewItems(categoryItems)

    }
}