package com.example.projectoneecommerecemvvm.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerecemvvm.databinding.CartItemsBinding
import com.example.projectoneecommerecemvvm.model.data.Cart
import com.example.projectoneecommerecemvvm.model.local.AppDatabase
import com.example.projectoneecommerecemvvm.model.remote.Constants
import com.squareup.picasso.Picasso

class CartAdapter(
    private val appdb:AppDatabase,
    private val cartItemsList: List<Cart>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private lateinit var binding: CartItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        binding = CartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItemsList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItemsList[position])
    }

    inner class CartViewHolder(binding: CartItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: Cart) {
            with(binding) {
                var price = cartItem.price
                var quantity = cartItem.quantity
                txtCartItemPrice.text = price.toString()
                txtCartItemName.text = cartItem.productName
                txtCartItemDescription.text = cartItem.description
                Picasso.get().load("${cartItem.productImageUrl}").into(cartItemImg)
                txtCartItemQuantity.text= quantity.toString()
                btnCartItemAddQuantity.setOnClickListener {
                    quantity++
                    txtCartItemQuantity.text= quantity.toString()
                    appdb.cartDao().insert(Cart(cartItem.product_id,quantity,cartItem.productName,cartItem.description,cartItem.price,cartItem.productImageUrl))
                }
                btnCartItemSubQuantity.setOnClickListener {
                    quantity--
                    if(quantity>=1){
                        txtCartItemQuantity.text= quantity.toString()
                        appdb.cartDao().insert(Cart(cartItem.product_id,quantity,cartItem.productName,cartItem.description,cartItem.price,cartItem.productImageUrl))
                    }
                    else{
                        quantity=1
                        txtCartItemQuantity.text= "1"
                    }
                }
                txtCartItemTotal.text = (price * quantity).toString()
            }

        }
    }
}