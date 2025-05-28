package com.example.hiltpractice.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltpractice.data.model.Store
import com.example.hiltpractice.domain.GetStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getStoresUseCase: GetStoreUseCase
) :ViewModel() {

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores.asStateFlow()

    init {
        viewModelScope.launch {
            getStoresUseCase.execute().collect {
                _stores.value = it
            }
        }
    }

    fun addStore(name: String) {
        val id = UUID.randomUUID().toString()
        val store = Store(id = id, name = name)
        viewModelScope.launch {
            getStoresUseCase.add(store)
        }
    }
}