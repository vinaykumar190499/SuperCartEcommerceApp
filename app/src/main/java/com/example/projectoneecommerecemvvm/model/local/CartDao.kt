package com.example.projectoneecommerecemvvm.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projectoneecommerecemvvm.model.data.Cart

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cart: Cart):Long

    @Query("select * from cart")
    fun getAllCartItems():LiveData<List<Cart>>

    @Query("select quantity from cart where productid = :id")
    fun getQuantityById(id:String):Int
}