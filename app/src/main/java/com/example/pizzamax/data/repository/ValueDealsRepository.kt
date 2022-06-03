package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.flow.Flow

class ValueDealsRepository(private val valueDealsDao: ValueDealsDao) : ValueDealsDao {
    override suspend fun insertToRoom(valueDeals: ValuesDeals) {
        valueDealsDao.insertToRoom(valueDeals)
    }

    override suspend fun updateList(valueDeals: ValuesDeals) {
        return valueDealsDao.updateList(valueDeals)
    }


    override fun getAll(): Flow<List<ValuesDeals>> {
        return valueDealsDao.getAll()
    }

    override suspend fun deleteAll() {
        return valueDealsDao.deleteAll()
    }

}