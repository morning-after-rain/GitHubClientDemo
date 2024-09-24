package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class SearchUserRepository {

    suspend fun search(keywords: String, page: Int, perPage: Int) =
        RetrofitClient.apiService.searchUsers(keywords, page, perPage)

//    suspend fun getUsers(since: Int, page: Int, perPage: Int) =
//        RetrofitClient.apiService.getUsers(since, page, perPage)


}