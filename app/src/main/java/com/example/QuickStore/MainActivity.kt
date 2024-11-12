package com.example.QuickStore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.QuickStore.feature.main.navigation.Navigation
import com.example.QuickStore.ui.theme.QuickStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickStoreTheme {
                Navigation()
            }
        }
    }
}