package com.example.QuickStore.feature.main.components.homeComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.QuickStore.R
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.model.cerita.CeritaModel
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Purple10

@Composable
fun CeritaCard(cerita: CeritaModel, navController: NavController) {
    val truncatedIsi = if (cerita.isi.length > 50) {
        "${cerita.isi.take(50)}..."
    } else {
        cerita.isi
    }

    Box(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .background(color = Black1, shape = RoundedCornerShape(15))
            .shadow(
                elevation = 1.dp,
                RoundedCornerShape(15)
            )
            .clickable {
                navController.navigate("${Screen.BacaCerita.route}/${cerita.id}") {
                    popUpTo(Screen.Cerita.route) {
                        inclusive = true
                    }
                }
            },
    ){
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.profile), contentDescription = "profile", modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = cerita.penulis, style = MaterialTheme.typography.titleLarge, color = Purple10)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth(0.6f)) {
                    Text(text = cerita.judul, style = MaterialTheme.typography.titleLarge, color = Purple10)
                    Text(text = truncatedIsi, style = MaterialTheme.typography.labelSmall, color = Purple10)
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RectangleShape)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = cerita.gambar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
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
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = "iconBookmark",
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
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "iconShare",
                        tint = Purple10
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "5126", style = MaterialTheme.typography.labelSmall, color = Purple10)
                }
            }
        }
    }
}