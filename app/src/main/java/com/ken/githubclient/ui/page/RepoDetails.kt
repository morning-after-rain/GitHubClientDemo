package com.ken.githubclient.ui.page

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun RepoDetails(navController: NavHostController, repoId: Long) {
    // 使用 repoId 来展示对应的详情内容
    // 例如：
    Text(text = "Repo Details for ID: $repoId")
}