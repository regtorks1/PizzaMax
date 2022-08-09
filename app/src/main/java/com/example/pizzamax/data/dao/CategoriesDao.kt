package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.relation.CategoryAndItems
import com.example.pizzamax.model.relation.CategoryWithCategoriesItems
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToCategories(categories: Categories):Long

    @Update
    fun updateCategories(categories: Categories)

    @Query("SELECT * FROM categories WHERE name =:query ORDER BY categoryId ASC")
    fun getAllFromCategories(query: String): Flow<List<Categories>>

    @Query("DELETE FROM categories")
    fun deleteAllFromCategories()

    @Delete
    fun deleteCategory(categories: Categories)//single item

    @Transaction
    @Query("SELECT * FROM categories inner join category_list on  categories.categoryId = category_list.items_id ")
    fun getCategoryWithItems(): Flow<List<CategoryWithCategoriesItems>>
}