package com.example.QuickStore.feature.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.components.ceritaComp.CeritaCard
import com.example.QuickStore.feature.main.navigation.BottomNavigationBar
import com.example.QuickStore.feature.main.section.profileSection.AppBar
import com.example.QuickStore.ui.theme.Black4
import com.example.QuickStore.ui.theme.Black5
import com.example.QuickStore.ui.theme.Purple5

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {
    val viewModel : ProfileViewModel = viewModel()

    Scaffold(bottomBar = {
        BottomAppBar(
            tonalElevation = 1.dp,
            containerColor = Black4,
        ) {
            BottomNavigationBar(navController = navController)
        }
    }) {
        Box(modifier = Modifier
            .background(Color.White)
            .padding(16.dp)){
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item {
                    AppBar(navController = navController, viewModel.user.value?.nama ?: "")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ceritaku",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Purple5
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = Black5))
                }
                items(viewModel.cerita) { cerita ->
                    Spacer(modifier = Modifier.height(16.dp))
                    CeritaCard(cerita = cerita, navController = navController, uid = viewModel.auth.currentUser?.uid ?: "")
                }
            }
        }
    }
}