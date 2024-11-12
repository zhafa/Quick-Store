package com.example.QuickStore.feature.bacaCerita

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.example.QuickStore.model.cerita.CeritaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BacaCeritaViewModel: ViewModel() {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val repository = Repository(auth, firestore)
    val cerita = mutableStateOf<CeritaModel?>(null)


    fun getCeritabyId(
        id: String
    ){
        repository.getCeritabyId(
            id,
            onSuccess = {
                cerita.value = it
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }
}