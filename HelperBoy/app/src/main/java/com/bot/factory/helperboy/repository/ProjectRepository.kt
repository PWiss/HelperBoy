package com.bot.factory.helperboy.repository

class ProjectRepository (
    private val firebase: FirebaseProjectFun
){

    fun creatProject(project: String) = firebase.createProject(project)

}