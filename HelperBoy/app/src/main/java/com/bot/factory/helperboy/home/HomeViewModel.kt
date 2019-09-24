package com.bot.factory.helperboy.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.bot.factory.helperboy.auth.data.UserRepository
import com.bot.factory.helperboy.util.startLoginActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    /*fun logout2(view: View){
        repository.logout()
        //view.context.startLoginActivity()
    }*/
    fun logout () {
        repository.logout()
    }

}
