package com.example.QuickStore.feature.main.components.buttonComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.example.QuickStore.ui.theme.Purple10


@Composable
fun BackPage(onClick: () -> Unit, text: String) {
    Box(contentAlignment = Alignment.TopStart) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "logo back",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onClick() }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = text, style = MaterialTheme.typography.displayMedium, color = Purple10)
        }

    }
}