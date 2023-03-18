package com.batueksi.tekrar.domain.usecase

import com.batueksi.tekrar.domain.repository.AuthRepository
import com.batueksi.tekrar.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseSignUpUseCase @Inject constructor(private val authRepository: AuthRepository){

    suspend operator fun invoke(email:String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val isSignUpSuccesfully = authRepository.signUp(email, password)
        if (isSignUpSuccesfully) {
            emit(Resource.Success(true))
        } else {
            emit(Resource.Error("Sign up error"))
        }
    }
}