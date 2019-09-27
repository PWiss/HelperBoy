package com.bot.factory.helperboy.createProject

import androidx.lifecycle.ViewModel
import com.bot.factory.helperboy.auth.AuthListener
import com.bot.factory.helperboy.repository.ProjectListener
import com.bot.factory.helperboy.repository.ProjectRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CreateProjectViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    //Projectname
    var projectName: String? = null

    //project listener
    var projectListener: ProjectListener? = null


    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()

    fun create(){
        if (projectName.isNullOrEmpty() ) {
            projectListener?.onFailure("Invalid email or password")
            return
        }
        val disposable = repository.creatProject(projectName!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //sending a success callback
                projectListener?.onSuccess()
            }, {
                //sending a failure callback
                projectListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }


}