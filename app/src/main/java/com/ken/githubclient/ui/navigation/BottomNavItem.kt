package com.ken.githubclient.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, "热门")
    object Discover : BottomNavItem("discover", Icons.Filled.Search, "搜索")
    object My : BottomNavItem("my", Icons.Filled.Person, "我的")
}