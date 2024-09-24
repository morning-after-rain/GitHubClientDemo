package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class SearchResultRepository {
    companion object {
        const val PARAM_TYPE = "param_type"
        const val PARAM_TYPE_REPO = "param_type_repo"
        const val PARAM_TYPE_USER = "param_type_user"
    }

    suspend fun search(keywords: String, page: Int, perPage: Int, type: String) =
        when (type) {
            PARAM_TYPE_USER -> RetrofitClient.apiService.searchUsers(keywords, page, perPage)
            else -> RetrofitClient.apiService.searchRepos(keywords, page, perPage)
        }


}