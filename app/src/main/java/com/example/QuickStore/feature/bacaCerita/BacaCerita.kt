package com.example.QuickStore.feature.bacaCerita

import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.QuickStore.feature.main.components.buttonComp.BackPage
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Black6
import com.example.QuickStore.ui.theme.Purple9
import com.example.QuickStore.feature.downloadCerita.DownloadCerita
import com.example.QuickStore.model.cerita.CeritaModel

@Composable
fun BacaCerita(navController: NavController, cerita_id: String) {
    val viewModel : BacaCeritaViewModel = viewModel()
    val context = LocalContext.current // Mendapatkan context di sini
    LaunchedEffect(key1 = true){
        viewModel.getCeritabyId(cerita_id)
    }

    Box(modifier = Modifier
        .background(color = Black1)
        .padding(16.dp)){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                BackPage(onClick = {
                    navController.navigate(Screen.Cerita.route) {
                        popUpTo("${Screen.BacaCerita.route}/{cerita_id}") { inclusive = true }
                    }
                }, text = "Baca Cerita")
                Spacer(modifier = Modifier.height(32.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = viewModel.cerita.value?.judul ?: "",
                        style = MaterialTheme.typography.displayMedium,
                        color = Purple9,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "iconLove",
                            tint = Black6
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Black6)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.Comment,
                            contentDescription = "iconComment",
                            tint = Black6
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Black6)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = "iconBookmark",
                            tint = Black6
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Black6)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(onClick = { DownloadCerita(context, viewModel.cerita.value) }) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download Cerita",
                            tint = Color.Blue
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp), contentAlignment = Alignment.Center) {
                    Image(
                        painter = rememberAsyncImagePainter(model = viewModel.cerita.value?.gambar ?: ""),
                        contentDescription = "gambar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight()
                            .clip(shape = RoundedCornerShape(10.dp))
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = viewModel.cerita.value?.penulis ?: "", style = MaterialTheme.typography.titleSmall, color = Black6)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Aug 30, 2024", style = MaterialTheme.typography.titleSmall, color = Black6)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = viewModel.cerita.value?.isi ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    color = Purple9,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

