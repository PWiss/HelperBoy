package com.bot.factory.helperboy.repository

interface ProjectListener {

    fun onSuccess()
    fun onFailure(message: String)
}