package com.batueksi.tekrar.di

import com.batueksi.tekrar.domain.repository.AuthRepository
import com.batueksi.tekrar.domain.repository.FirebaseAuthRepositoryImpl
import com.batueksi.tekrar.domain.repository.FirestoreUserRepositoryImpl
import com.batueksi.tekrar.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository : FirebaseAuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindUserRepository(userRepository: FirestoreUserRepositoryImpl): UserRepository
}