package com.batueksi.tekrar.di

import com.batueksi.tekrar.domain.repository.AuthRepository
import com.batueksi.tekrar.domain.repository.FirebaseAuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository : FirebaseAuthRepositoryImpl): AuthRepository
}