package com.example.projectoneecommerecemvvm.view.smartphone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectoneecommerecemvvm.R
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.databinding.FragmentSmartPhoneBinding
import com.example.projectoneecommerecemvvm.model.Repository
import com.example.projectoneecommerecemvvm.model.data.subcategory.Subcategory
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneListVMFactory
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhoneListViewModel
import com.example.projectoneecommerecemvvm.viewmodel.smartphone.SmartPhonesListAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SmartPhoneFragment : Fragment() {
    lateinit var binding:FragmentSmartPhoneBinding
    val viewModel:SmartPhoneListViewModel by viewModels(){
        val subCategory = arguments?.getParcelable<Subcategory>("SMARTPHONE_CATEGORY")
        val id = subCategory?.subcategory_id?.toInt().let{
            it
        }
        SmartPhoneListVMFactory(Repository(ApiService.getInstance()),1)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmartPhoneBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.apiResourceForSmartPhonesList.observe(viewLifecycleOwner){
            when(it){
                is ApiResource.Error -> {
                    val dialog = AlertDialog.Builder(requireContext()).apply{
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
                    binding.recyclerViewSmartPhones.layoutManager = LinearLayoutManager(context)
                    binding.recyclerViewSmartPhones.adapter = SmartPhonesListAdapter(it.result.products)

                }
            }
        }
    }


}