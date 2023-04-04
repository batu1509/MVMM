package com.batueksi.moviesapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun updateUIMode(uiMode: Int)

    fun getUIMode(): Flow<Int>

    suspend fun saveLoginInfo(uid: String)

    fun getLoginInfo(): Flow<String?>

    suspend fun  deleteLoginInfo()
}