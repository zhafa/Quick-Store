package com.example.QuickStore.feature.main.section.profileSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.QuickStore.R
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.feature.settingsScreen.SettingsViewModel
import com.example.QuickStore.ui.theme.Purple1
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple9

@Composable
fun AppBar(navController: NavController, nama: String, settingsViewModel: SettingsViewModel = viewModel()) {
    // Observasi status logout
    val logoutSuccess by settingsViewModel.logoutSuccess

    LaunchedEffect(logoutSuccess) {
        if (logoutSuccess) {
            // Navigasi ke layar login setelah logout berhasil
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier.size(55.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = nama,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Purple10
                    )
                    Text(
                        text = "274 Pengikut",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Purple10
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(Purple1, shape = CircleShape)
                    .clickable {
                        // Navigasi ke SettingsScreen
                        navController.navigate(Screen.Settings.route) {
                            popUpTo(Screen.Home.route) { inclusive = false }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "iconSetting",
                    tint = Purple9
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bio
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "ini bio.",
                style = MaterialTheme.typography.labelLarge,
                color = Purple10
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}
