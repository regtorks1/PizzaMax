package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.SignaturePizza
import kotlinx.coroutines.flow.Flow


@Dao
interface SignatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToRoom(signaturePizza: SignaturePizza)

    @Update
     fun updateList(signaturePizza: SignaturePizza)

    @Query("SELECT * FROM signature ORDER BY id ASC")
    fun getAllFromSignature(): Flow<List<SignaturePizza>>

    @Query("DELETE FROM signature")
    fun deleteAllFromSignature()

     @Delete
     fun deleteItem(signaturePizza: SignaturePizza)//single item
}