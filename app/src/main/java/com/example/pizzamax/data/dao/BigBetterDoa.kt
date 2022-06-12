package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.BigBetter
import kotlinx.coroutines.flow.Flow

@Dao
interface BigBetterDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(bigBetter: BigBetter)

    @Update
    suspend fun updateList(bigBetter: BigBetter)

    @Query("SELECT * FROM big_better ORDER BY id ASC")
    fun getAllFromBigBetter(): Flow<List<BigBetter>>

    @Query("DELETE FROM big_better")
    suspend fun deleteFromBigBetter()
}