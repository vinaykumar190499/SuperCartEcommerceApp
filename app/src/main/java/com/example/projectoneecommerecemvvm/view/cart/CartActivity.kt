package com.example.projectoneecommerecemvvm.view.cart

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectoneecommerecemvvm.R
import com.example.projectoneecommerecemvvm.databinding.ActivityCartBinding
import com.example.projectoneecommerecemvvm.model.local.AppDatabase

class CartActivity : AppCompatActivity() {
    lateinit var binding:ActivityCartBinding
    lateinit var appdb:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setUpObservers()
    }

    private fun setUpObservers() {
        appdb.cartDao().getAllCartItems().observe(this){
            binding.recyclerView.layoutManager=LinearLayoutManager(this)
            binding.recyclerView.adapter= CartAdapter(appdb,it)
            var totalSum=0.0
            it.forEach {
                cartItem->
                    totalSum+=cartItem.price*cartItem.quantity
            }
            binding.txtTotalCartCheckOutPriceSum.text = totalSum.toString()
        }
    }

    private fun initViews() {
        appdb=AppDatabase.getInstance(this@CartActivity)!!
    }
}