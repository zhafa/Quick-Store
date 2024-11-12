package com.example.QuickStore.feature.main.section.homeSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.components.buttonComp.LihatButton
import com.example.QuickStore.feature.main.components.homeComp.CeritaCard
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.model.cerita.CeritaModel
import com.example.QuickStore.ui.theme.Purple10

@Composable
fun RekomCerita(navController: NavController, cerita: List<CeritaModel>) {
    val limitedCerita = cerita.take(3)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Rekomendasi Cerita", style = MaterialTheme.typography.titleLarge, color = Purple10)
            LihatButton(onClick = {
                navController.navigate(Screen.Cerita.route) { popUpTo(Screen.Home.route) { inclusive = true } }
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        limitedCerita.forEach { ceritaItem ->
            CeritaCard(cerita = ceritaItem, navController = navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}