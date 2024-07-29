package com.example.projectoneecommerecemvvm.view.smartphone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.databinding.ActivitySmartPhoneDetailedViewBinding
import com.example.projectoneecommerecemvvm.model.Repository
import com.example.projectoneecommerecemvvm.model.data.smartPhone.Product
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import com.example.projectoneecommerecemvvm.utils.Utils.PRODUCT_INFO
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneDetailedInfoVMFactory
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneDetailedInfoViewModel
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneVMFactory
import com.squareup.picasso.Picasso

class SmartPhoneDetailedInfoViewActivity : AppCompatActivity() {
    lateinit var binding:ActivitySmartPhoneDetailedViewBinding
    lateinit var viewModel :SmartPhoneDetailedInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySmartPhoneDetailedViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.apiResourceForSmartPhoneDetailedInfo.observe(this){
            when(it){
                is ApiResource.Error -> {
                    val dialog = AlertDialog.Builder(this).apply{
                        setTitle("Error")
                        setMessage(it.error)
                        create()
                    }.setPositiveButton("Ok"){
                            dialog,which->
                        dialog.dismiss()
                    }
                    dialog.show()
                }
                is ApiResource.Loading -> TODO()
                is ApiResource.Success -> {
                   with(binding){
                       val product = it.result.product
                       customRatingBar.rating = product.average_rating.toFloat()
                       txtPhoneTitle.text = product.product_name
                       txtPhoneDesc.text = product.description
                       txtPhonePricee.text = "$ ${product.price}"
                       Picasso.get().load("${product?.product_image_url}").into(phoneImg)
                       txtModel.text= product.specifications[0].specification
                       txtColor.text= product.specifications[1].specification
                       txtRam.text = product.specifications[3].specification
                       txtInternalStorage.text = product.specifications[4].specification
                       txtProcessor.text = product.specifications[11].specification
                       txtPrimaryCamera.text = product.specifications[5].specification
                       txtFrontCamera.text = product.specifications[7].specification
                       txtDisplay.text = product.specifications[9].specification
                       txtOperationSystem.text = product.specifications[2].specification
                       //recyclerViewUserReview.layoutManager = LinearLayoutManager(this@SmartPhoneDetailedViewActivity)
                   }

                }
            }
        }

    }

    private fun initViews() {
        val product = intent.getParcelableExtra<Product>(PRODUCT_INFO)
        val apiService = ApiService.getInstance()
        val repository = Repository(apiService)
        val id = product?.product_id?.toInt().let{
            it
        }
        val factory = SmartPhoneDetailedInfoVMFactory(repository,id?:1)
        viewModel = ViewModelProvider(this,factory).get(SmartPhoneDetailedInfoViewModel::class.java)
    }
}