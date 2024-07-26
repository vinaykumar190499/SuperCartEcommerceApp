package com.example.projectoneecommerecemvvm.model.data.dashboard

data class GetDashBoardResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)