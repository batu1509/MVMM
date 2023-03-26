package com.batueksi.tekrar.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batueksi.tekrar.domain.repository.DataStoreOperations
import com.batueksi.tekrar.domain.usecase.GetUIModeUseCase
import com.batueksi.tekrar.helper.Constants.SPLASH_SCREEN_DELAY
import com.batueksi.tekrar.presentation.splash.SplashEvent
import com.batueksi.tekrar.presentation.splash.SplashFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUIModeUseCase: GetUIModeUseCase,
    private val dataStoreOperations: DataStoreOperations
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<SplashEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        getNavigateAfterSplashScreenDelay()
        getUiMode()
    }

    @VisibleForTesting
    fun getUiMode() {
        viewModelScope.launch {
            _eventFlow.emit(SplashEvent.UpdateUiMode(getUIModeUseCase().first()))
        }
    }

    private fun getNavigateAfterSplashScreenDelay() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            dataStoreOperations.getLoginInfo().collect {
                if (it.isNullOrEmpty()) {
                    _eventFlow.emit(SplashEvent.NavigateTo(SplashFragmentDirections.actionSplashFragmentToLoginFragment()))
                }
                else {
                    _eventFlow.emit(SplashEvent.NavigateTo(SplashFragmentDirections.actionSplashFragmentToHomeFragment2(it)))
                }
            }
        }
    }
}

