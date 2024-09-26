package com.ken.githubclient.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.ext.concat
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.model.SearchRepoResult
import com.ken.githubclient.net.model.SearchUserResult
import com.ken.githubclient.net.model.UserInfo
import com.ken.githubclient.net.repository.SearchResultRepository
import com.ken.githubclient.net.repository.SearchResultRepository.Companion.PARAM_TYPE_USER
import com.ken.githubclient.utls.Logger


class SearchResultViewModel : BaseViewModel() {

    companion object {
        const val TAG = "SearchResultViewModel"
        const val INITIAL_PAGE = 1
        const val INITIAL_PER_PAGE = 10
    }

    private val searchResultRepository by lazy { SearchResultRepository() }

    val repoList = MutableLiveData<MutableList<RepoEntity>>()
    val userInfoList = MutableLiveData<MutableList<UserInfo>>()

    val refreshStatus = MutableLiveData<Boolean>()
    val mLoadStatus = MutableLiveData<LoadStatus>()
    val reloadStatus = MutableLiveData<Boolean>()
    val emptyStatus = MutableLiveData<Boolean>()
    val loadFirstPageStatus = MutableLiveData<LoadStatus>()

    private var currentKeywords = ""
    private var page = INITIAL_PAGE

    private lateinit var mType: String

    fun search(keywords: String = currentKeywords, type: String) {
        Logger.d("searchStart", "currentKeywords:${currentKeywords} type:${type}")
        launch(block = {
            if (currentKeywords != keywords) {
                currentKeywords = keywords
//                repoList.value = emptyList<RepoEntity>().toMutableList()
            }

            mType = type

            refreshStatus.value = true
            emptyStatus.value = false
            reloadStatus.value = false

            val searchResult =
                searchResultRepository.search(keywords, INITIAL_PAGE, INITIAL_PER_PAGE, mType)
            page = 2
            if (mType == PARAM_TYPE_USER) {
                val searchUserResult = searchResult as SearchUserResult
                userInfoList.value = searchUserResult.items
                refreshStatus.value = false
                emptyStatus.value = searchUserResult.items.isEmpty()
            } else {
                val searchRepoResult = searchResult as SearchRepoResult
                Logger.d("searchResult", searchRepoResult.toString())
                repoList.postValue(searchRepoResult.items)
                refreshStatus.value = false
                emptyStatus.value = searchRepoResult.items.isEmpty()
            }
            loadFirstPageStatus.postValue(LoadStatus.COMPLETED)
            Logger.d("searchResult", "step2")
        }, error = {
            Logger.d("searchResult", "fail:${it.message}")
            println(it.message)
            refreshStatus.value = false
            reloadStatus.value = page == INITIAL_PAGE
            repoList.postValue(emptyList<RepoEntity>().toMutableList())
            loadFirstPageStatus.postValue(LoadStatus.ERROR)
        })
    }

    fun loadMore() {
        Logger.d(TAG, "loadMore page:{$page}")
        launch(block = {
            mLoadStatus.value = LoadStatus.LOADING
            val searchResult =
                searchResultRepository.search(currentKeywords, page, INITIAL_PER_PAGE, mType)
            page++
            if (mType == PARAM_TYPE_USER) {
                val searchUserResult = searchResult as SearchUserResult
                userInfoList.value = userInfoList.value.concat(searchUserResult.items)
                mLoadStatus.value = if (searchUserResult.incomplete_results) {
                    LoadStatus.END
                } else {
                    LoadStatus.COMPLETED
                }
            } else {
                val searchRepoResult = searchResult as SearchRepoResult
                val currentList = repoList.value?.toMutableList() ?: mutableListOf()
                currentList.addAll(searchRepoResult.items)
                repoList.postValue(currentList)
                mLoadStatus.value = if (searchRepoResult.incomplete_results) {
                    LoadStatus.END
                } else {
                    LoadStatus.COMPLETED
                }
                Logger.d("searchResult loadMore", searchRepoResult.toString())
            }

        }, error = {
            println(it.message)
            mLoadStatus.value = LoadStatus.ERROR
        })
    }

}


data class SearchUIState(
    val repoEntityList: List<RepoEntity> = emptyList(),
    val openedRepoEntity: RepoEntity? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)