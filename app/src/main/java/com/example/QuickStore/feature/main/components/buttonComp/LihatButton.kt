package com.example.QuickStore.feature.main.components.buttonComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.QuickStore.R


@Composable
fun LihatButton(onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick() }) {
        Text(text = "Lihat semua", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.width(5.dp))
        Image(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "icon_arrow", modifier = Modifier.size(12.dp))
    }
}