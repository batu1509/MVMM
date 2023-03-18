package com.batueksi.tekrar.domain.usecase

import com.batueksi.tekrar.domain.repository.AuthRepository
import com.batueksi.tekrar.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(private val authRepository: AuthRepository){

    suspend operator fun invoke(email:String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val loggedSuccessfully = authRepository.login(email, password)
        if (loggedSuccessfully){
            emit(Resource.Success(true))
            emit(Resource.Finished)
        }else{
            emit(Resource.Error("Login error"))
            emit(Resource.Finished)
        }
    }
}