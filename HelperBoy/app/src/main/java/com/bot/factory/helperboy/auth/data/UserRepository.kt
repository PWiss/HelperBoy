package com.bot.factory.helperboy.auth.data

class UserRepository (
    private val firebase: FirebaseAuthFun
){
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}