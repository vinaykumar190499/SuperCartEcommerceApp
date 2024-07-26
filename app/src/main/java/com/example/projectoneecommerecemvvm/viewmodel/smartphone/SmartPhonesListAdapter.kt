package com.example.projectoneecommerecemvvm.viewmodel.smartphone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerecemvvm.databinding.SmartphoneItemsBinding
import com.example.projectoneecommerecemvvm.model.data.smartPhone.Product
import com.example.projectoneecommerecemvvm.model.remote.Constants
import com.squareup.picasso.Picasso

class SmartPhonesListAdapter(
    val productsLst: List<Product>,
//    val onSelectPhone: (SmartPhones) -> Unit,
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
                txtPhoneName.text = products.product_name
                txtPhoneDescription.text= products.description
                val pricetext = "$ ${ products.price }"
                txtPhonePrice.text = pricetext
                Picasso.get().load("${Constants.IMG_BASE_URL}${products.product_image_url}").into(smartphoneImg)
            }
//            binding.smartphonesCard.setOnClickListener{
//                onSelectPhone(smartPhones)
//            }
//            binding.btnAddToCart.setOnClickListener{
//                println("here ______________________________________________________________")
//                println("call this shit")
//                onSelctAddToCart(smartPhones)
//            }
        }

    }
}