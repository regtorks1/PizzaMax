package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.SignaturePizza
import kotlinx.coroutines.flow.Flow


@Dao
interface SignatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(signaturePizza: SignaturePizza)

    @Update
    suspend fun updateList(signaturePizza: SignaturePizza)

    @Query("SELECT * FROM signature ORDER BY id ASC")
    fun getAllFromSignature(): Flow<List<SignaturePizza>>

    @Query("DELETE FROM signature")
    suspend fun deleteAllFromSignature()

     @Delete
    suspend fun deleteItem(signaturePizza: SignaturePizza)//single item
}