package com.example.QuickStore.model.user

data class UserModel(
    val uid : String,
    val nama : String,
    val biodata : String,
    val phqScore : Long,
    val premium : Boolean
)