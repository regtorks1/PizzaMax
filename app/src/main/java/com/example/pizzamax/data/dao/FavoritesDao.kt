package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Favorites
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavorites(favorites: List<Favorites>)

    @Update
    suspend fun updateList(favorites: Favorites)

    @Query("SELECT * FROM favorites ORDER BY id ASC")
    fun getAllFromFavorites(): Flow<List<Favorites>>

    @Query("DELETE FROM favorites")
    suspend fun deleteFromFavorites()

     @Delete
    suspend fun deleteItem(favorites: Favorites)//single item
}