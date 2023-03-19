package com.batueksi.tekrar.domain.repository

import com.bumptech.glide.load.engine.Resource

interface AuthRepository {

    suspend fun login(email:String , password: String): String

    suspend fun signUp(email: String, password: String): String
}