package com.example.QuickStore.feature.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.components.buttonComp.BackPage
import com.example.QuickStore.feature.main.components.notifComp.NotifCard
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple5

@Composable
fun Notification(navController: NavController) {
    Box(modifier = Modifier
        .background(Color.White)
        .padding(16.dp)){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    BackPage(onClick = {
                        navController.navigate(Screen.Home.route) { popUpTo(Screen.Notification.route) { inclusive = true } }
                    }, text = "Notifikasi")
                    Text(text = "Tandai baca semua", style = MaterialTheme.typography.bodyMedium, color = Purple5)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Hari Ini", style = MaterialTheme.typography.titleLarge, color = Purple10)
                Spacer(modifier = Modifier.height(16.dp))
                NotifCard()
            }
        }
    }
}