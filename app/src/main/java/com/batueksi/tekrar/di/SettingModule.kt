package com.batueksi.tekrar.di

import com.batueksi.tekrar.domain.SettingUseCase
import com.batueksi.tekrar.domain.repository.DataStoreOperations
import com.batueksi.tekrar.domain.usecase.GetUIModeUseCase
import com.batueksi.tekrar.domain.usecase.UpdateUIModeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {

    @Provides
    @Singleton
    fun provideSettingsUseCases(
        dataStoreOperations: DataStoreOperations
    ): SettingUseCase {
        return SettingUseCase(
            getUIModeUseCase = GetUIModeUseCase(dataStoreOperations),
            updateUIModeUseCase = UpdateUIModeUseCase(dataStoreOperations),
        )
    }
}