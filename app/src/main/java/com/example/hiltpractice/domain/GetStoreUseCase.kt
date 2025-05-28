package com.example.hiltpractice.domain

import com.example.hiltpractice.data.StoreRepository
import com.example.hiltpractice.data.model.Store
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    fun execute(): Flow<List<Store>> = repository.observeStores()
    suspend fun loadOnce(): List<Store> = repository.getStores()
    suspend fun add(store: Store) = repository.addStore(store)
}