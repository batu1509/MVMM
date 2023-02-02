package com.batueksi.tekrar.domain.usecase

import com.batueksi.tekrar.domain.repository.DataStoreOperations
import javax.inject.Inject

class UpdateUIModeUseCase @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend operator fun invoke(uiMode: Int) {
        dataStoreOperations.updateUIMode(uiMode)
    }
}