package com.example.QuickStore.feature.settingsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.route.Screen

@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel = viewModel()) {
    // Observasi status logout
    val logoutSuccess by settingsViewModel.logoutSuccess

    LaunchedEffect(logoutSuccess) {
        if (logoutSuccess) {
            // Navigasi ke layar login setelah logout berhasil
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.Settings.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Atur teks ke bagian atas layar
    ) {
        // Teks judul Settings di bagian atas
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 24.dp) // Memberikan jarak dari atas
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Logout
        Button(
            onClick = { settingsViewModel.logout() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Logout", color = Color.White)
        }
    }
}
