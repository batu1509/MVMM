package com.batueksi.tekrar.presentation.splash

import androidx.navigation.NavDirections

sealed class SplashEvent {
    data class NavigateTo(val directions: NavDirections) : SplashEvent()
    data class UpdateUiMode(val uiMode: Int) : SplashEvent()
}