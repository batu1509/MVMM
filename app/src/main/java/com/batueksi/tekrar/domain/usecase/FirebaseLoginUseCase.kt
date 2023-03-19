package com.batueksi.tekrar.domain.usecase

import com.batueksi.tekrar.data.models.auth.User
import com.batueksi.tekrar.domain.repository.AuthRepository
import com.batueksi.tekrar.domain.repository.UserRepository
import com.batueksi.tekrar.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository) {

    suspend operator fun invoke(email:String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        val userUID = authRepository.login(email, password)
        if (userUID.isNotEmpty()){

            val user = userRepository.getUser(uid= userUID)

            emit(Resource.Success(user))
            emit(Resource.Finished)
        }else{
            emit(Resource.Error("Login error"))
            emit(Resource.Finished)
        }
    }
}