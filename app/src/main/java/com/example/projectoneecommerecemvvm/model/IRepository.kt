package com.example.projectoneecommerecemvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.data.dashboard.GetDashBoardResponse
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneResponse
import com.example.projectoneecommerecemvvm.model.data.subcategory.GetSubCategoryResponse

interface IRepository {
    val _apiResourceForDashBoard : MutableLiveData<ApiResource<GetDashBoardResponse>>
    val apiResourceForDashBoard: LiveData<ApiResource<GetDashBoardResponse>>
    val _apiResourceForSubCategory : MutableLiveData<ApiResource<GetSubCategoryResponse>>
    val apiResourceForSubCategory: LiveData<ApiResource<GetSubCategoryResponse>>
    val _apiResourceForSmartPhone : MutableLiveData<ApiResource<GetSmartPhoneResponse>>
    val apiResourceForSmartPhone: LiveData<ApiResource<GetSmartPhoneResponse>>
    fun getDashBoard()
    fun getSmartPhoneSubCategory(id:Int)
    fun getSmartPhones(id:Int)
}