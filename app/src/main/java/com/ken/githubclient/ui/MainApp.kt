package com.ken.githubclient.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ken.githubclient.ui.navigation.BottomNavItem
import com.ken.githubclient.ui.navigation.BottomNavigationBar
import com.ken.githubclient.ui.page.HotScreen
import com.ken.githubclient.ui.page.UserCenterScreen
import com.ken.githubclient.ui.page.SearchScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HotScreen()
            }
            composable(BottomNavItem.Discover.route) {
                SearchScreen()
            }
            composable(BottomNavItem.My.route) {
                UserCenterScreen()
            }
        }
    }

}