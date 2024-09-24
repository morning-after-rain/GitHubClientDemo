package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient
import com.ken.githubclient.net.model.UserInfo


class LoginRepository {

    val USER_ACCESS_TOKEN = ""
    suspend fun login(): UserInfo {
        val auth = "token $USER_ACCESS_TOKEN"
        return RetrofitClient.apiService.fetchUserOwner(auth)
    }

}


