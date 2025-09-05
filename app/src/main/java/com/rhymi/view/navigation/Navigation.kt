package com.rhymi.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rhymi.view.ui.AddClassScreen
import com.rhymi.view.ui.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(
            route = "home"
        ) {
            HomeScreen(
                addClassButtonOnClick = { navController.navigate("add_class") }
            )
        }
        composable(
            route = "add_class"
        ) {
            AddClassScreen(
                navController = navController
            )
        }
    }
}