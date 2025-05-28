package com.example.hiltpractice.data

import com.example.hiltpractice.data.model.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun addStore(store: Store)
    suspend fun getStores(): List<Store>
    fun observeStores(): Flow<List<Store>> // 실시간
}