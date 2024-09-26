package com.ken.githubclient.ui.viewmodel

import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.model.SearchRepoResult
import com.ken.githubclient.net.repository.SearchResultRepository
import com.ken.githubclient.ui.viewmodel.SearchResultViewModel.Companion.DEFAULT_SEARCH_KEY_WORD
import com.ken.githubclient.ui.viewmodel.SearchResultViewModel.Companion.INITIAL_PAGE
import com.ken.githubclient.utls.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class SearchResultViewModel : BaseViewModel() {

    companion object {
        const val TAG = "SearchResultViewModel"
        const val INITIAL_PAGE = 1
        const val INITIAL_PER_PAGE = 10
        const val DEFAULT_SEARCH_KEY_WORD = "stars:>1"
    }

    private val searchResultRepository by lazy { SearchResultRepository() }
    private val _uiState = MutableStateFlow(SearchResultUIState(loadStatus = LoadStatus.LOADING))
    val uiState: StateFlow<SearchResultUIState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        Logger.d(TAG, "observeSearch start")
        launch(block = {
            _uiState.value = _uiState.value.copy(loadStatus = LoadStatus.LOADING)
            val searchResult: SearchRepoResult = searchResultRepository.searchRepo(
                _uiState.value.currentKeywords, _uiState.value.page, INITIAL_PER_PAGE
            )
            _uiState.value.repoList.addAll(searchResult.items)
            val loadStatus = if (searchResult.incomplete_results) {
                LoadStatus.END
            } else {
                LoadStatus.COMPLETED
            }
            val page = _uiState.value.page + 1
            _uiState.value =
                _uiState.value.copy(page = page, loadStatus = loadStatus, isLoadMore = false)
            Logger.d(TAG, "search success :$searchResult")
        }, error = {
            _uiState.value = _uiState.value.copy(
                error = it.message,
                loadStatus = LoadStatus.ERROR,
                isLoadMore = false
            )
            Logger.d(TAG, "search fail :${it.message}")
        })

    }

    fun search(searchStr: String = DEFAULT_SEARCH_KEY_WORD) {
        Logger.d(TAG, "search searchStr :${searchStr}")
        if (_uiState.value.currentKeywords != searchStr) _uiState.value =
            SearchResultUIState(currentKeywords = searchStr)
        loadData()
    }

    fun reload() {
        Logger.d(TAG, "search reload")
        _uiState.value = _uiState.value.copy(page = INITIAL_PAGE)
        loadData()
    }

    fun loadMore() {
        Logger.d(TAG, "loadMore")
        _uiState.value = _uiState.value.copy(isLoadMore = true)
        loadData()
    }
}


data class SearchResultUIState(
    val repoList: MutableList<RepoEntity> = mutableListOf(),
    val loadStatus: LoadStatus = LoadStatus.LOADING,
    val isLoadMore: Boolean = false,
    val currentKeywords: String = DEFAULT_SEARCH_KEY_WORD,
    val page: Int = INITIAL_PAGE,
    val error: String? = null,
    val openedRepo: RepoEntity? = null,
    val isDetailOnlyOpen: Boolean = false,
)

