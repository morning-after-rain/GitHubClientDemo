package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class FollowerRepository {

    suspend fun getFollowers(userName: String) =
        RetrofitClient.apiService.getFollowers(userName)
}