package com.example.QuickStore.feature.main.components.buttonComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.QuickStore.R


@Composable
fun ButtonBack(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
        Image(painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "logo back",
            modifier = Modifier
                .size(28.dp)
                .clickable { onClick() })
    }
}