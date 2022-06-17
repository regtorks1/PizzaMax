package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.ProductRecyclerViewItem
import kotlinx.coroutines.flow.Flow

sealed interface ProductListDao {
    @Dao
    interface AppetizersDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToRoom(appetizers: ProductRecyclerViewItem.Appetizers)

        @Update
        fun updateList(appetizers: ProductRecyclerViewItem.Appetizers)

        @Query("SELECT * FROM appetizers ORDER BY id ASC")
        fun getAllFromAppetizers(): Flow<List<ProductRecyclerViewItem.Appetizers>>

        @Query("DELETE FROM appetizers")
        fun deleteAllFromAppetizers()

        @Delete
        fun deleteItem(appetizers: ProductRecyclerViewItem.Appetizers)//single item
    }

    @Dao
    interface BigBetterDoa {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToRoom(bigBetter: ProductRecyclerViewItem.BigBetter)

        @Update
        fun updateList(bigBetter: ProductRecyclerViewItem.BigBetter)

        @Query("SELECT * FROM big_better ORDER BY id ASC")
        fun getAllFromBigBetter(): Flow<List<ProductRecyclerViewItem.BigBetter>>

        @Query("DELETE FROM big_better")
        fun deleteFromBigBetter()

        @Delete
        fun deleteItem(bigBetter: ProductRecyclerViewItem.BigBetter)//single item
    }


}