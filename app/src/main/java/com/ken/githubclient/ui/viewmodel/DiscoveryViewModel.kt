package com.ken.githubclient.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.ext.concat
import com.ken.githubclient.net.model.UserInfo
import com.ken.githubclient.net.repository.DiscoveryRepository


class DiscoveryViewModel : BaseViewModel() {

    companion object {
        const val INITIAL_PAGE = 1
        const val INITIAL_PER_PAGE = 40
    }

    private val discoveryRepository by lazy { DiscoveryRepository() }

    val repoList = MutableLiveData<MutableList<UserInfo>>()

    val refreshStatus = MutableLiveData<Boolean>()
    val mLoadStatus = MutableLiveData<LoadStatus>()
    val reloadStatus = MutableLiveData<Boolean>()
    val emptyStatus = MutableLiveData<Boolean>()

    private var page = INITIAL_PAGE

    private var mSince: Int = 0

    fun getData(since: Int) {
        launch(
            block = {
                refreshStatus.value = true
                emptyStatus.value = false
                reloadStatus.value = false

                mSince = since

                val userInfoList = discoveryRepository.getUsers(mSince, INITIAL_PER_PAGE)
                page = 2
                repoList.value = userInfoList
                refreshStatus.value = false
                emptyStatus.value = userInfoList?.isEmpty()
                mLoadStatus.value = LoadStatus.COMPLETED
            },
            error = {
                println(it.message)
                refreshStatus.value = false
                reloadStatus.value = page == INITIAL_PAGE
            }
        )
    }

    fun loadMore() {
        launch(
            block = {
                mLoadStatus.value = LoadStatus.LOADING
                val userInfoList = discoveryRepository.getUsers(mSince, INITIAL_PER_PAGE)
                page++
                repoList.value = repoList.value.concat(userInfoList)

                mLoadStatus.value = LoadStatus.COMPLETED

            },
            error = {
                println(it.message)
                mLoadStatus.value = LoadStatus.ERROR
            }
        )
    }

}