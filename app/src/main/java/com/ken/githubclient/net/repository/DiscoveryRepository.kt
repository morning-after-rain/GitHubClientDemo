package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class DiscoveryRepository {

    suspend fun getUsers(since: Int, perPage: Int) =
        RetrofitClient.apiService.getUsers(since, perPage)
}