package com.ken.githubclient.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ken.githubclient.ui.navigation.BottomNavigationBar
import androidx.compose.ui.Modifier
import com.ken.githubclient.ui.navigation.BottomNavItem
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import com.ken.githubclient.ui.page.DiscoverPage
import com.ken.githubclient.ui.page.HomePage
import com.ken.githubclient.ui.page.MyPage

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
                HomePage()
            }
            composable(BottomNavItem.Discover.route) {
                DiscoverPage()
            }
            composable(BottomNavItem.My.route) {
                MyPage()
            }
        }
    }

}