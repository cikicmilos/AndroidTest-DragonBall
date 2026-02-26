package com.example.dragonballapp_cikicmilos.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dragonballapp_cikicmilos.presentation.characterdetail.CharacterDetailScreen
import com.example.dragonballapp_cikicmilos.presentation.characterlist.CharacterListScreen
import com.example.dragonballapp_cikicmilos.presentation.favorites.FavoritesScreen
import com.example.dragonballapp_cikicmilos.presentation.vscomparison.VsComparisonScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = Screen.bottomNavItems.any { screen ->
        currentDestination?.hierarchy?.any { it.route == screen.route } == true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    Screen.bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                screen.icon?.let { Icon(it, contentDescription = screen.title) }
                            },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.CharacterList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.CharacterList.route) {
                CharacterListScreen(
                    onCharacterClick = { id ->
                        navController.navigate(Screen.CharacterDetail.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.CharacterDetail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("id") ?: return@composable
                CharacterDetailScreen(
                    characterId = characterId,
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    onCharacterClick = { id ->
                        navController.navigate(Screen.CharacterDetail.createRoute(id))
                    }
                )
            }
            composable(Screen.VsComparison.route) {
                VsComparisonScreen()
            }
        }
    }
}
