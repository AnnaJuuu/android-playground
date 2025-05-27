package com.example.hiltpractice

import android.util.Log
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor() : StoreRepository {
    init {
        Log.d("HILT_CHECK", "StoreRepositoryImpl has been created")
    }
    override fun getStoreName(): String = "날씨 요정 가게"
}