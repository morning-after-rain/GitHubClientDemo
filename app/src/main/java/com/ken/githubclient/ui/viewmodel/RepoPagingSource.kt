package com.ken.githubclient.ui.viewmodel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.model.SearchRepoResult
import com.ken.githubclient.net.repository.SearchResultRepository
import com.ken.githubclient.net.repository.SearchResultRepository.Companion.PARAM_TYPE_REPO

class RepoPagingSource(private val repository: SearchResultRepository = SearchResultRepository()) :
    PagingSource<Int, RepoEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoEntity> {
        val page = params.key ?: 1
        return try {
            val repoList = repository.search(
                "",
                page,
                SearchResultViewModel.INITIAL_PER_PAGE,
                PARAM_TYPE_REPO
            ) as SearchRepoResult
            LoadResult.Page(
                data = repoList.items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repoList.incomplete_results) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}