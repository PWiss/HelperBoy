package com.bot.factory.helperboy

import android.app.Application
import com.bot.factory.helperboy.auth.AuthViewModelFactory
import com.bot.factory.helperboy.auth.data.FirebaseAuthFun
import com.bot.factory.helperboy.auth.data.UserRepository
import com.bot.factory.helperboy.createProject.CreateProjectViewModelFactory
import com.bot.factory.helperboy.home.HomeViewModelFactory
import com.bot.factory.helperboy.repository.FirebaseProjectFun
import com.bot.factory.helperboy.repository.ProjectRepository

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

@Suppress("unused")
class Application : Application(), KodeinAware{

    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))

        bind() from singleton { FirebaseAuthFun()}
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }

        bind() from singleton { FirebaseProjectFun()}
        bind() from singleton { ProjectRepository(instance()) }
        bind() from provider { CreateProjectViewModelFactory(instance()) }
        //bind() from provider { HomeViewModelFactory(instance()) }

    }
}