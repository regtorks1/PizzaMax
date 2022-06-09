package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.BigBetterDoa
import com.example.pizzamax.model.BigBetter
import kotlinx.coroutines.flow.Flow

class BigBetterRepository(private val bigBetterDoa: BigBetterDoa) : BigBetterDoa {
    override suspend fun insertToRoom(bigBetter: BigBetter) {
        bigBetterDoa.insertToRoom(bigBetter)
    }

    override suspend fun updateList(bigBetter: BigBetter) {
        return bigBetterDoa.updateList(bigBetter)
    }

    override fun getAllFromBigBetter(): Flow<List<BigBetter>> {
       return bigBetterDoa.getAllFromBigBetter()
    }

    override suspend fun deleteFromBigBetter() {
        return bigBetterDoa.deleteFromBigBetter()
    }

}