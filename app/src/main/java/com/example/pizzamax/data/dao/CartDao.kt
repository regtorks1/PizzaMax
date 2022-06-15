package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Cart
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(cart: List<Cart>)

    @Update
    suspend fun updateList(cart: Cart)

    @Query("SELECT * FROM cart ORDER BY id ASC")
    fun getAllFromCart(): Flow<List<Cart>>

    @Query("DELETE FROM cart")
    suspend fun deleteFromCart()//all item

    @Query("SELECT* FROM cart WHERE price LIKE :amount")
    fun searchAmount(amount: String) : Flow<List<Cart>>

    @Delete
    suspend fun deleteItem(cart: Cart)//single item
}