package com.ken.githubclient.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.repository.ReposRepository

class ReposViewModel : BaseViewModel() {

    private val reposRepository by lazy { ReposRepository() }

    val repoList = MutableLiveData<MutableList<RepoEntity>>()

    val refreshStatus = MutableLiveData<Boolean>()
    val mLoadStatus = MutableLiveData<LoadStatus>()
    val reloadStatus = MutableLiveData<Boolean>()
    val emptyStatus = MutableLiveData<Boolean>()

    fun getPros(userName: String) {
        launch(
            block = {

                refreshStatus.value = true
                emptyStatus.value = false
                reloadStatus.value = false

                val reposList = reposRepository.getPros(userName)
                repoList.value = reposList
                refreshStatus.value = false
                emptyStatus.value = reposList?.isEmpty()
            },
            error = {
                println(it.message)
                refreshStatus.value = false
                reloadStatus.value = true
            }
        )
    }

}