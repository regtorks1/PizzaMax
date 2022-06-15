package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Cart
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToRoom(cart: List<Cart>)

    @Update
     fun updateList(cart: Cart)

    @Query("SELECT * FROM cart ORDER BY id ASC")
    fun getAllFromCart(): Flow<List<Cart>>

    @Query("DELETE FROM cart")
    fun deleteFromCart()//all item

    @Delete
    fun deleteItem(cart: Cart)//single item
}