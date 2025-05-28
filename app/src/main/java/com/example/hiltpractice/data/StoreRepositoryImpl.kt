package com.example.hiltpractice.data

import com.example.hiltpractice.data.model.Store
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : StoreRepository {

    private val storeCollection = firestore.collection("stores")

    override suspend fun addStore(store: Store) {
        storeCollection.document(store.id).set(store).await()
    }

    override suspend fun getStores(): List<Store> {
        return storeCollection.get().await().documents.mapNotNull {
            it.toObject(Store::class.java)?.copy(id = it.id)
        }
    }

    override fun observeStores(): Flow<List<Store>> = callbackFlow {
        val listener = storeCollection.addSnapshotListener { snapshot, _ ->
            val list = snapshot?.documents?.mapNotNull {
                it.toObject(Store::class.java)?.copy(id = it.id)
            } ?: emptyList()
            trySend(list)
        }
        awaitClose { listener.remove() }
    }
}