package com.example.QuickStore.feature.main.section.ceritaSection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple6

@Composable
fun AppBar(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Ruang Cerita", style = MaterialTheme.typography.displaySmall, color = Purple10)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector =  Icons.Outlined.Search,
                    contentDescription = "iconSearch",
                    tint = Purple10
                )
                Spacer(modifier = Modifier.width(5.dp))
                Box(modifier = Modifier.background(color = Black1, shape = RoundedCornerShape(100)).padding(7.dp).clickable {
                    navController.navigate(Screen.CeritaKu.route) { popUpTo(Screen.Cerita.route) { inclusive = true } }
                }){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector =  Icons.Outlined.Book,
                            contentDescription = "iconBook",
                            tint = Purple10
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "Ceritaku", style = MaterialTheme.typography.titleSmall, color = Purple10)
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))

                Box(
                    modifier = Modifier.background(color = Black1, shape = RoundedCornerShape(100)).padding(7.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector =  Icons.Outlined.BookmarkBorder,
                            contentDescription = "iconNotif",
                            tint = Purple10
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Purple6))
    }
}