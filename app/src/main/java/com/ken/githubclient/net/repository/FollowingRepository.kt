package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class FollowingRepository {

    suspend fun getFollowing(userName: String) =
        RetrofitClient.apiService.getFollowing(userName)
}