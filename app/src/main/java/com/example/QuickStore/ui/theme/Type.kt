package com.example.QuickStore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.QuickStore.R


// Set of Material typography styles to start with
val Typography = Typography(
    //Display
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_extrabold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 44.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_extrabold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 38.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_extrabold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
    ),

    //Headline
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 39.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp,
    ),

    //Title
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),

    //Label
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),

    //Body
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
)