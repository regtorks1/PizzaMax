package com.example.pizzamax.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.flow.Flow


@Dao
interface ValueDealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(locationDetails: ValuesDeals)

    @Query("SELECT * FROM deals ORDER BY id ASC")
    fun getAll(): Flow<List<ValuesDeals>>

    @Query("DELETE FROM deals")
    suspend fun deleteAll()
}