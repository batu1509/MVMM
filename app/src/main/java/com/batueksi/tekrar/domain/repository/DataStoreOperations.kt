package com.batueksi.tekrar.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun updateUIMode(uiMode: Int)

    fun getUIMode(): Flow<Int>
}