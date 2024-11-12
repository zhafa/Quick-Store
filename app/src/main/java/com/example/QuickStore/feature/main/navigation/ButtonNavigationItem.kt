package com.example.QuickStore.feature.main.navigation

import com.example.QuickStore.R
import com.example.QuickStore.feature.main.route.Screen


sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String
) {
    object Home: BottomNavigationItem(route = Screen.Home.route, icon = R.drawable.beranda, label = "Beranda")
    object Cerita: BottomNavigationItem(route = Screen.Cerita.route, icon = R.drawable.cerita, label = "Banding")
    object Profile: BottomNavigationItem(route = Screen.Profile.route, icon = R.drawable.profil, label = "Profil")
}