package com.ken.githubclient.net.model

data class SearchUserResult(
    val incomplete_results: Boolean,
    val items: MutableList<UserInfo>,
    val total_count: Int
)