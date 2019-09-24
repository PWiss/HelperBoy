package com.bot.factory.helperboy.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bot.factory.helperboy.auth.data.UserRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}