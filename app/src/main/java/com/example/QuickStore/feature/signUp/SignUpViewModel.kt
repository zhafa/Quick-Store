package com.example.QuickStore.feature.signUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.QuickStore.data.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel: ViewModel() {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val authRepository = Repository(auth, firestore)

    val isLoading = mutableStateOf(false)
    val isSuccess = mutableStateOf(false)
    val errMsg = mutableStateOf("")

    fun signUp(
        email : String,
        password : String,
        nama : String,
    ){
        isLoading.value = true
        isSuccess.value = false
        authRepository.signUp(
            email,
            password,
            nama,
            onSuccess = {
                isSuccess.value = true
                isLoading.value = false
                Log.e("Berhasil", "Sign Up Berhasil")
            },
            onFailed = {
                isLoading.value = false
                isSuccess.value = false
                errMsg.value = it.toString()
            }
        )
    }
}