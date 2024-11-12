package com.example.QuickStore.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.QuickStore.feature.bacaCerita.BacaCerita
import com.example.QuickStore.feature.buatCerita.BuatCerita
import com.example.QuickStore.feature.cerita.Cerita
import com.example.QuickStore.feature.ceritaKu.CeritaKu
import com.example.QuickStore.feature.changePass.ChangePass
import com.example.QuickStore.feature.editCerita.EditCerita
import com.example.QuickStore.feature.forgotPass.ForgotPass
import com.example.QuickStore.feature.home.Home
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.feature.notification.Notification
import com.example.QuickStore.feature.onBoard.OnBoard
import com.example.QuickStore.feature.otp.Otp
import com.example.QuickStore.feature.profile.Profile
import com.example.QuickStore.feature.signIn.SignIn
import com.example.QuickStore.feature.signUp.SignUp
import com.example.QuickStore.feature.splash.SplashScreen
import com.example.QuickStore.feature.settingsScreen.SettingsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.OnBoard.route) {
            OnBoard(navController = navController)
        }

        composable(route = Screen.SignIn.route) {
            SignIn(navController = navController)
        }

        composable(route = Screen.SignUp.route) {
            SignUp(navController = navController)
        }

        composable(route = Screen.ForgotPass.route) {
            ForgotPass(navController = navController)
        }

        composable(route = Screen.ChangePass.route) {
            ChangePass(navController = navController)
        }

        composable(route = Screen.Otp.route) {
            Otp(navController = navController)
        }

        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }

        composable(route = Screen.Notification.route) {
            Notification(navController = navController)
        }

        composable(route = Screen.Cerita.route) {
            Cerita(navController = navController)
        }

        composable(route = "${Screen.BacaCerita.route}/{cerita_id}",
            arguments = listOf(
                navArgument("cerita_id") {
                    type = NavType.StringType
                }
            ))
        {
            val cerita_id = it.arguments?.getString("cerita_id") ?: ""
            BacaCerita(navController = navController, cerita_id)
        }


        composable(route = Screen.BuatCerita.route) {
            BuatCerita(navController = navController)
        }

        composable(route = Screen.CeritaKu.route) {
            CeritaKu(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            Profile(navController = navController)
        }


        composable(route = "${Screen.EditCerita.route}/{cerita_id}",
            arguments = listOf(
                navArgument("cerita_id") {
                    type = NavType.StringType
                }
            ))
        {
            val cerita_id = it.arguments?.getString("cerita_id") ?: ""
            EditCerita(navController = navController, cerita_id)
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}