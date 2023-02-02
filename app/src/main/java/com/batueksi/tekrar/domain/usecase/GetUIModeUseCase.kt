package com.batueksi.tekrar.domain.usecase

import com.batueksi.tekrar.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUIModeUseCase @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    operator fun invoke(): Flow<Int> {
        return dataStoreOperations.getUIMode()
    }
}