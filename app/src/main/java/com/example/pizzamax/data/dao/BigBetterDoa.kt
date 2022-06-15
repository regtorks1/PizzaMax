package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.BigBetter
import kotlinx.coroutines.flow.Flow

@Dao
interface BigBetterDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToRoom(bigBetter: BigBetter)

    @Update
     fun updateList(bigBetter: BigBetter)

    @Query("SELECT * FROM big_better ORDER BY id ASC")
    fun getAllFromBigBetter(): Flow<List<BigBetter>>

    @Query("DELETE FROM big_better")
     fun deleteFromBigBetter()

     @Delete
     fun deleteItem(bigBetter: BigBetter)//single item
}