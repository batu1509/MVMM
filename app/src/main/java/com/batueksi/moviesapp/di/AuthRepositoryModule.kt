package com.batueksi.moviesapp.di

import com.batueksi.moviesapp.domain.repository.AuthRepository
import com.batueksi.moviesapp.data.repository.FirebaseAuthRepositoryImpl
import com.batueksi.moviesapp.data.repository.FirestoreUserRepositoryImpl
import com.batueksi.moviesapp.domain.repository.UserRepository
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