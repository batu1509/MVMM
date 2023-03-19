package com.batueksi.tekrar.domain.repository

import com.batueksi.tekrar.data.models.auth.User

interface UserRepository {

    suspend fun createUser(user: User) : Boolean

    suspend fun getUser(uid: String): User
}