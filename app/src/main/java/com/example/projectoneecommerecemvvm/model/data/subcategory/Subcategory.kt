package com.example.projectoneecommerecemvvm.model.data.subcategory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subcategory(
    val category_id: String,
    val is_active: String,
    val subcategory_id: String,
    val subcategory_image_url: String,
    val subcategory_name: String
):Parcelable