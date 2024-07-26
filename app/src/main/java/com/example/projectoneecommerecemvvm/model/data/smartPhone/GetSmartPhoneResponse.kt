package com.example.projectoneecommerecemvvm.model.data.smartPhone

data class GetSmartPhoneResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)