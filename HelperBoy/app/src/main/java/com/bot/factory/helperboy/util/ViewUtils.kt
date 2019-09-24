package com.bot.factory.helperboy.util

import android.content.Context
import android.content.Intent
import com.bot.factory.helperboy.home.HomeFragment
import com.bot.factory.helperboy.auth.LoginFragment

fun Context.startHomeActivity() =
    Intent(this, HomeFragment::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginFragment::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }