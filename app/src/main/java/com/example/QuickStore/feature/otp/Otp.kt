package com.example.QuickStore.feature.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.QuickStore.feature.main.components.buttonComp.ButtonBack
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Black4
import com.example.QuickStore.ui.theme.Black5
import com.example.QuickStore.ui.theme.Black6
import com.example.QuickStore.ui.theme.Black7
import com.example.QuickStore.ui.theme.Black8
import com.example.QuickStore.ui.theme.Purple6

@Composable
fun Otp(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val isFormFilled = email.value.isNotEmpty()
    var otpCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ButtonBack(onClick = {
            navController.navigate(Screen.SignIn.route) { popUpTo(Screen.Otp.route) { inclusive = true } }
        })
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Masukkan kode OTP",
            style = MaterialTheme.typography.displayMedium,
            color = Black8
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Masukkan kode 4 digit yang Anda terima di email Anda.",
            style = MaterialTheme.typography.bodySmall,
            color = Black7
        )
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Black5)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OTPInputBoxes(
            otpCode = otpCode,
            onOtpCodeChange = { newCode ->
                if (newCode.length <= 4) {
                    otpCode = newCode
                }
            }
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPInputBoxes(
    otpCode: String,
    onOtpCodeChange: (String) -> Unit
) {
    var focusedIndex by remember { mutableStateOf(0) }

    fun handleInput(newInput: String) {
        val sanitizedInput = newInput.filter { it.isDigit() }
        if (sanitizedInput.length <= 4) {
            onOtpCodeChange(sanitizedInput)
            if (sanitizedInput.length < 4) {
                focusedIndex = sanitizedInput.length
            }
        }
    }

    fun handleBoxClick(index: Int) {
        focusedIndex = index
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        repeat(4) { index ->
            OutlinedTextField(
                value = otpCode.getOrNull(index)?.toString() ?: "",
                onValueChange = { newChar ->
                    if (newChar.length == 1 && newChar[0].isDigit()) {
                        val newOtpCode = otpCode.toMutableList()
                        if (index < newOtpCode.size) {
                            newOtpCode[index] = newChar[0]
                        } else if (index == newOtpCode.size) {
                            newOtpCode.add(newChar[0])
                        }
                        handleInput(newOtpCode.joinToString(""))
                        // Move focus to the next box if there is room for more characters
                        if (index < 3 && newOtpCode.size > index) {
                            focusedIndex = index + 1
                        }
                    }
                },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = Black6,
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                modifier = Modifier
                    .size(70.dp)
                    .border(
                        width = 1.dp,
                        color = if (focusedIndex == index) Purple6 else Black6,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { handleBoxClick(index) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }
    }
}
