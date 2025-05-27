package com.example.hiltpractice

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getStoreNameUseCase: GetStoreNameUseCase
) :ViewModel() {

    init {
        Log.d("HILT_CHECK", "StoreViewModel injected with GetStoreNameUseCase")
    }

    val storeName = getStoreNameUseCase()
}