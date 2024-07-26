package com.example.projectoneecommerecemvvm.view.smartphone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.R
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.databinding.ActivitySmartPhoneBinding
import com.example.projectoneecommerecemvvm.model.Repository
import com.example.projectoneecommerecemvvm.model.data.dashboard.Category
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import com.example.projectoneecommerecemvvm.utils.Utils
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneVMFactory
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneViewModel
import com.google.android.material.tabs.TabLayoutMediator

class SmartPhoneActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySmartPhoneBinding
    lateinit var viewModel :SmartPhoneViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySmartPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setupObservers()
    }

    private fun initViewModel() {
        val category = intent.getParcelableExtra<Category>(Utils.DASHBOARD_CATEGORY)
        val apiService = ApiService.getInstance()
        val repository = Repository(apiService)
        val id = category?.category_id?.toInt().let{
            it
        }
        val factory = SmartPhoneVMFactory(repository,id?:1)
        viewModel = ViewModelProvider(this,factory).get(SmartPhoneViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.apiResource.observe(this){
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
                    val adapter = SmartPhoneViewPagerAdapter(this,it.result.subcategories)
                    binding.viewPager.adapter=adapter
                    TabLayoutMediator(binding.tabLayout,binding.viewPager){
                            tab,position->
                        tab.text = "${it.result.subcategories[position].subcategory_name}"
                    }.attach()

                }
            }
        }
    }
}