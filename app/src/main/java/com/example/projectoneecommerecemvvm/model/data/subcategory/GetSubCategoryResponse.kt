package com.example.projectoneecommerecemvvm.model.data.subcategory

data class GetSubCategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)