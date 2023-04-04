package com.batueksi.moviesapp.domain.repository

import com.batueksi.moviesapp.data.models.auth.User

interface UserRepository {

    suspend fun createUser(user: User) : Boolean

    suspend fun getUser(uid: String): User
}