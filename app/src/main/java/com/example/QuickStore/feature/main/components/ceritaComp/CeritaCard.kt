package com.example.QuickStore.feature.main.components.ceritaComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.QuickStore.R
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.model.cerita.CeritaModel
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple9


@Composable
fun CeritaCard(navController: NavController, cerita: CeritaModel, uid: String) {
    val truncatedIsi = if (cerita.isi.length > 100) {
        "${cerita.isi.take(120)}..."
    } else {
        cerita.isi
    }

    Box(modifier = Modifier
        .background(color = Black1, shape = RoundedCornerShape(12.dp))
        .padding(16.dp)
        .clickable {
            navController.navigate("${Screen.BacaCerita.route}/${cerita.id}") {
                popUpTo(Screen.Cerita.route) {
                    inclusive = true
                }
            }
        }){
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.profile), contentDescription = "profile", modifier = Modifier.size(30.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = cerita.penulis, style = MaterialTheme.typography.titleLarge, color = Purple10)
                }
                if(cerita.user_id == uid) {
                    Box(
                        modifier = Modifier
                            .background(color = Black1, shape = RoundedCornerShape(100))
                            .border(1.dp, color = Purple9, shape = RoundedCornerShape(100))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = {
                                    // Navigasi ke halaman EditCerita dengan mengirimkan ID cerita
                                    navController.navigate("${Screen.EditCerita.route}/${cerita.id}")
                                }
                            ) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "iconPen",
                                    tint = Purple9
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Edit cerita",
                                style = MaterialTheme.typography.titleSmall,
                                color = Purple10
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberAsyncImagePainter(model = cerita.gambar),
                    contentDescription = "gambar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cerita.judul,
                style = MaterialTheme.typography.titleMedium,
                color = Purple10
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = truncatedIsi,
                style = MaterialTheme.typography.bodyMedium,
                color = Purple10,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "1 jam lalu", style = MaterialTheme.typography.bodySmall, color = Purple10)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "iconLove",
                            tint = Purple10
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Purple10)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.Comment,
                            contentDescription = "iconComment",
                            tint = Purple10
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Purple10)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = "iconBookmark",
                            tint = Purple10
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Purple10)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "iconShare",
                        tint = Purple10
                    )
                }
            }
        }
    }
}