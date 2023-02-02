package com.batueksi.tekrar.domain

import com.batueksi.tekrar.domain.usecase.GetUIModeUseCase
import com.batueksi.tekrar.domain.usecase.UpdateUIModeUseCase

data class SettingUseCase(
    val getUIModeUseCase: GetUIModeUseCase,
    val updateUIModeUseCase: UpdateUIModeUseCase,
)