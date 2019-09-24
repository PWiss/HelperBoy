package com.bot.factory.helperboy.home

import androidx.lifecycle.ViewModel
import com.bot.factory.helperboy.auth.data.UserRepository

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }
    fun logout () {
        repository.logout()
    }

}
