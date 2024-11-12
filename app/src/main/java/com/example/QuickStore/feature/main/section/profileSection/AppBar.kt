package com.example.QuickStore.feature.main.section.profileSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.QuickStore.R
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Purple1
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple9


@Composable
fun AppBar(navController: NavController, nama: String) {
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.profile), contentDescription = "profile", modifier = Modifier.size(55.dp))
                Spacer(modifier = Modifier.width(15.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = nama,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Purple10
                    )
                    Text(
                        text = "274 Pengikut",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Purple10
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(Purple1, shape = CircleShape)
                    .clickable {
                        navController.navigate(Screen.Notification.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center,
                content = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector =  Icons.Outlined.Settings,
                        contentDescription = "iconSetting",
                        tint = Purple9
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            Text(text = "ini bio.", style = MaterialTheme.typography.labelLarge, color = Purple10)
        }
    }
}