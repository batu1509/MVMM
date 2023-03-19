package com.batueksi.tekrar.domain.repository

import com.batueksi.tekrar.data.models.auth.User
import com.batueksi.tekrar.helper.Constants.USERS_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreUserRepositoryImpl @Inject constructor(): UserRepository{

    override suspend fun createUser(user: User): Boolean {
        return try {
            var isSuccessfull = false
            FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
                .document(user.uid)
                .set(user, SetOptions.merge())
                .addOnCompleteListener { isSuccessfull = it.isSuccessful }
                .await()
            isSuccessfull
        } catch (e:Exception){
            false
        }
    }

    override suspend fun getUser(uid: String): User {
        return try {
            var loggedUser = User()
            FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
                .document(uid)
                .get()
                .addOnSuccessListener {
                    loggedUser = it.toObject(User::class.java)!!
                }
                .await()
            loggedUser
        } catch (e:Exception){
            User()
        }
    }
}