package com.bot.factory.helperboy.createProject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bot.factory.helperboy.repository.ProjectRepository

@Suppress("UNCHECKED_CAST")
class CreateProjectViewModelFactory(
    private val repository: ProjectRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateProjectViewModel(repository) as T
    }

}
