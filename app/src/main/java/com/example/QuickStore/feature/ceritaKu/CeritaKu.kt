package com.example.QuickStore.feature.ceritaKu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.components.buttonComp.BackPage
import com.example.QuickStore.feature.main.components.ceritaComp.CeritaCard
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple3
import com.example.QuickStore.ui.theme.Purple6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CeritaKu(navController: NavController) {
    val viewModel: CeritaKuViewModel = viewModel()

    val categories = listOf(
        "All", "Romantis", "Keluarga", "Karir", "Hubungan",
        "Finansial", "Makanan", "Pendidikan", "Inspirasi",
        "Psikologi", "Fantasi", "Lainnya"
    )

    LaunchedEffect(viewModel.isSelected.value) {
        if (viewModel.isSelected.value == "All") {
            viewModel.getAllUserCerita()
        } else {
            viewModel.getUserCeritabyKategori()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Purple3, Color.White)
                )
            )
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackPage(onClick = {
                        navController.navigate(Screen.Cerita.route) {
                            popUpTo(Screen.CeritaKu.route) {
                                inclusive = true
                            }
                        }
                    }, text = "Ceritaku")
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "iconSearch",
                        tint = Purple10
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        CategoryChip(
                            text = category,
                            isSelected = category == viewModel.isSelected.value,
                            viewModel = viewModel
                        )
                    }
                }
            }
            items(viewModel.cerita) { cerita ->
                Spacer(modifier = Modifier.height(16.dp))
                CeritaCard(
                    cerita = cerita,
                    navController = navController,
                    uid = viewModel.auth.currentUser?.uid ?: ""
                )
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple10
            ),
            shape = CircleShape,
            onClick = {
                navController.navigate(Screen.BuatCerita.route) {
                    popUpTo(Screen.Cerita.route) { inclusive = true }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "iconPen",
                tint = Black1

            )
        }
    }
}

@Composable
fun CategoryChip(text: String, isSelected: Boolean, viewModel: CeritaKuViewModel) {
    val backgroundColor = if (isSelected) Purple6 else Black1
    val textColor = if (isSelected) Black1 else Purple6

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                viewModel.isSelected.value = text
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.titleSmall
        )
    }
}