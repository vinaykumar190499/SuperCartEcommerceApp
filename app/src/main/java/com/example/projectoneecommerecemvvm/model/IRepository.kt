package com.example.projectoneecommerecemvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.data.dashboard.GetDashBoardResponse

interface IRepository {
    val _apiResource : MutableLiveData<ApiResource<GetDashBoardResponse>>
    val apiResource: LiveData<ApiResource<GetDashBoardResponse>>
    fun getDashBoard()
}