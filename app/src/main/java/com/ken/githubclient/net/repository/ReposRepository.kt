package com.ken.githubclient.net.repository

import com.ken.githubclient.net.RetrofitClient


class ReposRepository {

    suspend fun getPros(userName: String) =
        RetrofitClient.apiService.getPros(userName)
}