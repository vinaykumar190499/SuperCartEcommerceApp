package com.example.projectoneecommerecemvvm.model.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "productid")
    val product_id: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "productname")
    val productName: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "imageurl")
    val productImageUrl: String,

):Parcelable