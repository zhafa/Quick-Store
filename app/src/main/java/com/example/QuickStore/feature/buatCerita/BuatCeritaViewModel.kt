package com.example.QuickStore.feature.buatCerita

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BuatCeritaViewModel: ViewModel()  {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val authRepository = Repository(auth, firestore)
    val isSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    val errMsg = mutableStateOf("")


    fun createCerita(
        gambar: String?,
        isi: String,
        judul: String,
        kategori: MutableState<List<String>>,
    ){
        isLoading.value = true
        isSuccess.value = false
        authRepository.createCerita(
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
}