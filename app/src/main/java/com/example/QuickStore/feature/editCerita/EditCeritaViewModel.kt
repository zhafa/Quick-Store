package com.example.QuickStore.feature.editCerita

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.example.QuickStore.model.cerita.CeritaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.QuickStore.feature.settingsScreen.SettingsScreen
import com.example.QuickStore.feature.settingsScreen.SettingsViewModel

class EditCeritaViewModel : ViewModel() {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val authRepository = Repository(auth, firestore)
    val isSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    val errMsg = mutableStateOf("")
    val cerita = mutableStateOf<CeritaModel?>(null)

    fun getCeritabyId(
        id: String
    ) {
        authRepository.getCeritabyId(
            id,
            onSuccess = {
                cerita.value = it
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }

    fun editCerita(
        id: String,
        gambar: String?,
        isi: String,
        judul: String,
        kategori: MutableState<List<String>>,
    ) {
        isLoading.value = true
        isSuccess.value = false
        authRepository.editCerita(
            ceritaId = id,
            gambar = gambar,
            isi = isi,
            judul = judul,
            kategori = kategori.value,
            onSuccess = {
                isLoading.value = false
                isSuccess.value = it
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = it.toString()
            }
        )
    }

    // Fungsi baru untuk menghapus cerita
    fun deleteCerita(id: String) {
        isLoading.value = true
        authRepository.deleteCerita(
            ceritaId = id,
            onSuccess = {
                isLoading.value = false
                isSuccess.value = true // Menandai penghapusan berhasil
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = it.toString() // Menyimpan pesan error jika ada
            }
        )
    }
//    fun logout() {
//        authRepository.logout(
//            onSuccess = {
//                logoutSuccess.value = true
//            },
//            onFailed = { exception ->
//                logoutError.value = exception.message ?: "Logout failed"
//            }
//        )
//    }
}
