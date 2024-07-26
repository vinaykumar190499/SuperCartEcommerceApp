package com.example.projectoneecommerecemvvm.view.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.databinding.ActivityDashBoardBinding
import com.example.projectoneecommerecemvvm.model.Repository
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import com.example.projectoneecommerecemvvm.model.remote.RemoteRepository
import com.example.projectoneecommerecemvvm.viewmodel.dashboard.DashBoardVMFactory
import com.example.projectoneecommerecemvvm.viewmodel.dashboard.DashBoardViewModel

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDashBoardBinding
    lateinit var  viewModel : DashBoardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setupObservers()
    }

    private fun initViewModel() {
        val apiService = ApiService.getInstance()
        val repository = Repository(apiService)
        val factory = DashBoardVMFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DashBoardViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.apiResource.observe(this){
            when(it){
                is ApiResource.Error -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Error")
                        setMessage(it.error)
                        create()
                    }.show()
                }
                is ApiResource.Loading -> {


                }
                is ApiResource.Success -> {
                    with(binding){
                        dashboardRecyclerView.layoutManager = GridLayoutManager(this@DashBoardActivity, 2)
                        binding.dashboardRecyclerView.adapter = DashBoardAdapter(it.result.categories){ Category ->
                        }
                    }

                }
            }
        }
    }

}