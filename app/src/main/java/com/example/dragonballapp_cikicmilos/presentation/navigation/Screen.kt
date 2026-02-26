package com.example.dragonballapp_cikicmilos.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    data object CharacterList : Screen("characters", "Characters", Icons.Filled.People)
    data object Favorites : Screen("favorites", "Favorites", Icons.Filled.Favorite)
    data object VsComparison : Screen("vs", "VS", Icons.Filled.Compare)
    data object CharacterDetail : Screen("character/{id}", "Detail") {
        fun createRoute(id: Int) = "character/$id"
    }

    companion object {
        val bottomNavItems = listOf(CharacterList, Favorites, VsComparison)
    }
}
