package com.example.QuickStore.feature.settingsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SettingsViewModel : ViewModel() {
    // Inisialisasi Repository dengan FirebaseAuth dan FirebaseFirestore
    private val authRepository = Repository(
        auth = FirebaseAuth.getInstance(),
        firestore = FirebaseFirestore.getInstance()
    )

    // State untuk menandai status logout
    val logoutSuccess = mutableStateOf(false)
    val logoutError = mutableStateOf("")

    fun logout() {
        authRepository.logout(
            onSuccess = {
                logoutSuccess.value = true
            },
            onFailed = { exception ->
                logoutError.value = exception.message ?: "Logout failed"
            }
        )
    }
}
