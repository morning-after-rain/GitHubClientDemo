package com.ken.githubclient.ui.page


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ken.githubclient.ui.components.SearchBar

@Composable
fun DiscoverPage() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(40.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
        ) {
            SearchBar(onClick = { navController.navigate("RepoDetails/${"testid"}") })
        }
    }
}