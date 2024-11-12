package com.example.QuickStore.feature.ceritaKu

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.example.QuickStore.model.cerita.CeritaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CeritaKuViewModel: ViewModel() {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val repository = Repository(auth, firestore)
    val isSelected = mutableStateOf("All")
    val cerita = mutableStateListOf<CeritaModel>()

    fun getAllUserCerita(){
        repository.getAllUserCerita(
            onSuccess = {
                cerita.clear()
                cerita.addAll(it)
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }

    fun getUserCeritabyKategori(){
        repository.getUserCeritabyKategori(
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