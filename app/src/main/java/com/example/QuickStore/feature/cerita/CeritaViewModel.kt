package com.example.QuickStore.feature.cerita

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.example.QuickStore.model.cerita.CeritaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CeritaViewModel: ViewModel() {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val repository = Repository(auth, firestore)
    val isSelected = mutableStateOf("All")
    val cerita = mutableStateListOf<CeritaModel>()

    fun getAllCerita(){
        repository.getAllCerita(
            onSuccess = {
                cerita.clear()
                cerita.addAll(it)
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }

    fun getCeritabyKategori(){
        repository.getCeritabyKategori(
            isSelected.value,
            onSuccess = {
                cerita.clear()
                cerita.addAll(it)
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }
}