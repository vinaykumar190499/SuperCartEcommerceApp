package com.example.projectoneecommerecemvvm.model.data

data class GetLoginResponse(
    val message: String,
    val status: Int,
    val user: User
)