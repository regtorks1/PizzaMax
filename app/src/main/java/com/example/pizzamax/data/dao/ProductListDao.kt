package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
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

    @Dao
    interface CartDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToRoom(cart: List<ProductRecyclerViewItem.Cart>)

        @Update
        fun updateList(cart: ProductRecyclerViewItem.Cart)

        @Query("SELECT * FROM cart ORDER BY id ASC")
        fun getAllFromCart(): Flow<List<ProductRecyclerViewItem.Cart>>

        @Query("DELETE FROM cart")
        fun deleteFromCart()//all item

        @Delete
        fun deleteItem(cart: ProductRecyclerViewItem.Cart)//single item
    }

    @Dao
    interface ExpensesDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToRoom(expenses: ProductRecyclerViewItem.Expenses)

        @Update
        fun updateList(expenses: MutableList<ProductRecyclerViewItem.Expenses>)

        @Query("SELECT * FROM expenses ORDER BY id ASC")
        fun getAllExpenses(): Flow<List<ProductRecyclerViewItem.Expenses>>

        @Query("DELETE FROM expenses")
        fun deleteFromExpenses()

        @Delete
        fun deleteItem(expenses: ProductRecyclerViewItem.Expenses)//single item
    }


    @Dao
    interface FavoritesDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToFavorites(favorites: List<ProductRecyclerViewItem.Favorites>)

        @Update
        fun updateList(favorites: ProductRecyclerViewItem.Favorites)

        @Query("SELECT * FROM favorites ORDER BY id ASC")
        fun getAllFromFavorites(): Flow<List<ProductRecyclerViewItem.Favorites>>

        @Query("DELETE FROM favorites")
        fun deleteFromFavorites()

        @Delete
        fun deleteItem(favorites: MutableList<ProductRecyclerViewItem.Favorites>)//single item
    }


    @Dao
    interface SignatureDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToRoom(signaturePizza: ProductRecyclerViewItem.SignaturePizza)

        @Update
        fun updateList(signaturePizza: ProductRecyclerViewItem.SignaturePizza)

        @Query("SELECT * FROM signature ORDER BY id ASC")
        fun getAllFromSignature(): Flow<List<ProductRecyclerViewItem.SignaturePizza>>

        @Query("DELETE FROM signature")
        fun deleteAllFromSignature()

        @Delete
        fun deleteItem(signaturePizza: ProductRecyclerViewItem.SignaturePizza)//single item
    }

    @Dao
    interface ValueDealsDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertToRoom(valueDeals: ProductRecyclerViewItem.ValuesDeals)

        @Update
        fun updateList(valueDeals: ProductRecyclerViewItem.ValuesDeals)

        @Query("SELECT * FROM deals_table ORDER BY id ASC")
        fun getAll(): Flow<List<ProductRecyclerViewItem.ValuesDeals>>

        @Query("DELETE FROM deals_table")
        fun deleteAll(): Int

        @Delete
        fun deleteItem(valueDeals: ProductRecyclerViewItem.ValuesDeals)//single item
    }


}