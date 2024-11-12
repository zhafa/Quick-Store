package com.example.QuickStore.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.navigation.BottomNavigationBar
import com.example.QuickStore.feature.main.section.homeSection.AppBar
import com.example.QuickStore.feature.main.section.homeSection.RekomCerita
import com.example.QuickStore.ui.theme.Black4

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {
    val viewModel : HomeViewModel = viewModel()

    Scaffold(bottomBar = {
        BottomAppBar(
            tonalElevation = 1.dp,
            containerColor = Black4,
        ) {
            BottomNavigationBar(navController = navController)
        }
    }) {
        Box(modifier = Modifier.background(Color.White)){
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item {
                    AppBar(navController = navController, viewModel.user.value?.nama ?: "")
                    Spacer(modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    RekomCerita(navController, viewModel.cerita)
                }
            }
        }
    }
}