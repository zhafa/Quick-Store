package com.example.QuickStore.feature.forgotPass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.components.buttonComp.ButtonBack
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.feature.signIn.EmailField
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Black4
import com.example.QuickStore.ui.theme.Black5
import com.example.QuickStore.ui.theme.Black7
import com.example.QuickStore.ui.theme.Black8
import com.example.QuickStore.ui.theme.Purple6

@Composable
fun ForgotPass(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val isFormFilled = email.value.isNotEmpty()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        ButtonBack(onClick = {
            navController.navigate(Screen.SignIn.route) { popUpTo(Screen.ForgotPass.route) { inclusive = true } }
        })
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "Lupa Kata Sandi?", style = MaterialTheme.typography.displayMedium, color = Black8)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Silakan masukkan alamat email Anda untuk menerima kartu verifikasi.", style = MaterialTheme.typography.bodySmall, color = Black7)
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Black5)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Alamat Email", style = MaterialTheme.typography.bodyLarge, color = Black8)
        Spacer(modifier = Modifier.height(16.dp))
        EmailField(value = email.value, onValueChange = { email.value = it })
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = if (isFormFilled) Purple6 else Black4,
                    shape = RoundedCornerShape(100)
                )
                .clickable(enabled = isFormFilled) {
                    if (isFormFilled) {
                        navController.navigate(Screen.Otp.route) {
                            popUpTo(Screen.ForgotPass.route) { inclusive = true }
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lanjut",
                color = Black1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}