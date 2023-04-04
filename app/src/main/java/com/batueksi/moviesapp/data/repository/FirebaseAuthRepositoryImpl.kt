package com.batueksi.moviesapp.data.repository

import com.batueksi.moviesapp.domain.repository.AuthRepository
import com.batueksi.moviesapp.domain.repository.DataStoreOperations
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth : FirebaseAuth,
    private val dataStoreOperations: DataStoreOperations
): AuthRepository {
    override suspend fun login(email: String, password: String): String {
        return try {
            var userUID = ""
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    userUID = it.user?.uid ?: ""
                }
                .await()
            dataStoreOperations.saveLoginInfo(userUID)
            userUID
        }catch (e:Exception){
            ""
        }
    }

    override suspend fun signUp(email: String, password: String): String {
        return try {
            var userUID = ""
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    userUID = it.user?.uid ?: ""
                }
                .await()
            userUID
        } catch (e:Exception){
            ""
        }
    }
}