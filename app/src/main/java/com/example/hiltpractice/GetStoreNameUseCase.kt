package com.example.hiltpractice

import javax.inject.Inject

class GetStoreNameUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    operator fun invoke() : String {
        return repository.getStoreName() // 이거 때문에 useCase가 함수처럼 사용가능하대
    }
}