package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class SearchResultRepository {

    suspend fun searchUser(keywords: String, page: Int, perPage: Int) =
        RetrofitClient.apiService.searchUsers(keywords, page, perPage)

    suspend fun searchRepo(keywords: String, page: Int, perPage: Int) =
        RetrofitClient.apiService.searchRepos(keywords, page, perPage)


}