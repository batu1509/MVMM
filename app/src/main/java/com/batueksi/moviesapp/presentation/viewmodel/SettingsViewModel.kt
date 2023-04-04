package com.batueksi.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batueksi.moviesapp.domain.repository.DataStoreOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) : ViewModel() {


    fun getUIMode(): Flow<Int> {
        return dataStoreOperations.getUIMode()
    }

    fun updateUIMode(uiMode: Int) {
        viewModelScope.launch {
            dataStoreOperations.updateUIMode(uiMode)
        }
    }

    fun logoutUser() = viewModelScope.launch {
        dataStoreOperations.deleteLoginInfo()
    }

}