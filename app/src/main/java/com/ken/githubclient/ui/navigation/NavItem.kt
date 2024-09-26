package com.ken.githubclient.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ken.githubclient.R

sealed class NavItem(val route: String, val icon: ImageVector, val iconTextId: Int) {
    object Home : NavItem(ClientRoute.SEARCH, Icons.Filled.Search, R.string.tab_search)
    object Issue : NavItem(ClientRoute.ISSUE, Icons.Filled.Email, R.string.issue)
    object My : NavItem(ClientRoute.USER_CENTER, Icons.Filled.Person, R.string.tab_setting)
}

object ClientRoute {
    const val SEARCH = "Search"
    const val ISSUE = "Issue"
    const val USER_CENTER = "UserCenter"
    const val REPO_DETAIL = "RepoDetail"
}