package com.ken.githubclient.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.net.model.FollowersEntity
import com.ken.githubclient.net.repository.FollowerRepository

class FollowerViewModel : BaseViewModel() {


    private val followerRepository by lazy { FollowerRepository() }

    val followerList = MutableLiveData<MutableList<FollowersEntity>>()

    val refreshStatus = MutableLiveData<Boolean>()
    val mLoadStatus = MutableLiveData<LoadStatus>()
    val reloadStatus = MutableLiveData<Boolean>()
    val emptyStatus = MutableLiveData<Boolean>()

    fun getFollowers(userName: String) {
        launch(
            block = {

                refreshStatus.value = true
                emptyStatus.value = false
                reloadStatus.value = false

                val tempFollowerList = followerRepository.getFollowers(userName)
                followerList.value = tempFollowerList
                refreshStatus.value = false
                emptyStatus.value = tempFollowerList?.isEmpty()
            },
            error = {
                println(it.message)
                refreshStatus.value = false
                reloadStatus.value = true
            }
        )
    }

}