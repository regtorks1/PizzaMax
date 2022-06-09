package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.AppetizersDao
import com.example.pizzamax.data.dao.BigBetterDoa
import com.example.pizzamax.data.dao.SignatureDao
import com.example.pizzamax.data.dao.ValueDealsDao
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.BigBetter
import com.example.pizzamax.model.SignaturePizza
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val bigBetterDoa: BigBetterDoa,
    private val valueDealsDao: ValueDealsDao,
    private val appetizersDao: AppetizersDao,
    private val signatureDao: SignatureDao
) : ValueDealsDao, BigBetterDoa, AppetizersDao, SignatureDao {

    //BIG BETTER REPOSITORY
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


    //VALUE DEALS REPOSITORY
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


    //APPETIZERS REPOSITORY
    override suspend fun insertToRoom(appetizers: Appetizers) {
        appetizersDao.insertToRoom(appetizers)
    }

    override suspend fun updateList(appetizers: Appetizers) {
        return appetizersDao.updateList(appetizers)
    }

    override fun getAllFromAppetizers(): Flow<List<Appetizers>> {
        return appetizersDao.getAllFromAppetizers()
    }

    override suspend fun deleteAllFromAppetizers() {
        return appetizersDao.deleteAllFromAppetizers()
    }


    //SIGNATURE REPOSITORY
    override suspend fun insertToRoom(signaturePizza: SignaturePizza) {
       signatureDao.insertToRoom(signaturePizza)
    }

    override suspend fun updateList(signaturePizza: SignaturePizza) {
        return signatureDao.updateList(signaturePizza)
    }

    override fun getAllFromSignature(): Flow<List<SignaturePizza>> {
        return signatureDao.getAllFromSignature()
    }

    override suspend fun deleteAllFromSignature() {
        return signatureDao.deleteAllFromSignature()
    }

}