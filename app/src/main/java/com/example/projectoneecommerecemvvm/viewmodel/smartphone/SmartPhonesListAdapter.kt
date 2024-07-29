package com.example.projectoneecommerecemvvm.viewmodel.smartphone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerecemvvm.databinding.SmartphoneItemsBinding
import com.example.projectoneecommerecemvvm.model.data.Cart
import com.example.projectoneecommerecemvvm.model.data.smartPhone.Product
import com.example.projectoneecommerecemvvm.model.local.AppDatabase
import com.example.projectoneecommerecemvvm.model.remote.Constants
import com.squareup.picasso.Picasso

class SmartPhonesListAdapter(
    private val context: Context,
    val productsLst: List<Product>,
    val onSelectPhone: (Product) -> Unit,
//    val onSelctAddToCart: (SmartPhones) -> Unit
): RecyclerView.Adapter<SmartPhonesListAdapter.SmartphonesViewHolder>() {
    private lateinit var binding: SmartphoneItemsBinding
//    private lateinit var binding1: ActivitySmartPhonesInStoreBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartphonesViewHolder {
        binding = SmartphoneItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        binding1 = ActivitySmartPhonesInStoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SmartphonesViewHolder(binding)
    }

    override fun getItemCount(): Int = productsLst.size

    override fun onBindViewHolder(holder: SmartphonesViewHolder, position: Int) {
        holder.bind(productsLst[position])
    }

    inner class SmartphonesViewHolder(binding: SmartphoneItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(products: Product) {
            with(binding){
                val appDb = AppDatabase.getInstance(context.applicationContext)
                txtPhoneName.text = products.product_name
                txtPhoneDescription.text= products.description
                val pricetext = "$ ${ products.price }"
                txtPhonePrice.text = pricetext
                Picasso.get().load("${Constants.IMG_BASE_URL}${products.product_image_url}").into(smartphoneImg)
                var quantity =appDb?.cartDao()?.getQuantityById(products.product_id)?:1
                tvQuantity.text=quantity.toString()
                btnAddToCart.setOnClickListener {
                    btnAddToCart.visibility= View.GONE
                    quantityLayout.visibility = View.VISIBLE
                    appDb?.cartDao()?.insert(Cart(products.product_id,1,products.product_name,products.description,products.price.toDouble(),products.product_image_url))
                }
                btnIncrease.setOnClickListener {
                    quantity++
                    tvQuantity.text = quantity.toString()
                    appDb?.cartDao()?.insert(Cart(products.product_id,quantity,products.product_name,products.description,products.price.toDouble(),products.product_image_url))
                }
                btnDecrease.setOnClickListener {
                    quantity--
                    if(quantity>0){
                        tvQuantity.text = quantity.toString()
                        appDb?.cartDao()?.insert(Cart(products.product_id,quantity,products.product_name,products.description,products.price.toDouble(),products.product_image_url))
                    }else{
                        quantity=0
                        btnAddToCart.visibility= View.VISIBLE
                        quantityLayout.visibility = View.GONE
                        appDb?.cartDao()?.insert(Cart(products.product_id,0,products.product_name,products.description,products.price.toDouble(),products.product_image_url))
                    }
                }
            }
            binding.smartphonesCard.setOnClickListener{
                onSelectPhone(products)
            }
//            binding.btnAddToCart.setOnClickListener{
//                onSelctAddToCart(smartPhones)
//            }
        }

    }
}