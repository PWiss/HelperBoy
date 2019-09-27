package com.bot.factory.helperboy.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import io.reactivex.Completable

class FirebaseProjectFun {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }


    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser?.email.toString()

    val city = hashMapOf(

       currentUser() to true
    )

    fun createProject(project: String) = Completable.create { emitter ->
        firestore.collection("projects").document(project)
            .set(city, SetOptions.merge())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        val users = hashMapOf(
            project to true
        )
        firestore.collection("Users").document(currentUser())
            .set(users, SetOptions.merge())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }

    fun updateProject(project: String) = Completable.create { emitter ->
        currentUser().let {
            firestore.collection("projects").document(project)
                .set("2344" to "fsfs")
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }
    }
}