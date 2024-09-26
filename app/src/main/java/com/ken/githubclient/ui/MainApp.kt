package com.ken.githubclient.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ken.githubclient.ui.content.RepoDetailScreen
import com.ken.githubclient.ui.navigation.NavItem
import com.ken.githubclient.ui.navigation.BottomNavigationBar
import com.ken.githubclient.ui.content.SearchScreen
import com.ken.githubclient.ui.content.UserCenterScreen
import com.ken.githubclient.ui.navigation.ClientRoute

@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavItem.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(NavItem.Home.route) {
                SearchScreen()
            }
            composable(NavItem.Issue.route) {
                EmptyComingSoon()
            }
            composable(NavItem.My.route) {
                UserCenterScreen()
            }
        }
    }

}