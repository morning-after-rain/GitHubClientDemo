package com.ken.githubclient.ui.content

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RepoDetailScreen(repoId: String) {
    // 使用 repoId 来展示对应的详情内容
    Text("这是仓库详情页面，Repo ID: $repoId")
}