package com.example.projectoneecommerecemvvm.viewmodel.smartphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.IRepository
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneDetailedInfoResponse
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneResponse

class SmartPhoneDetailedInfoViewModel(private val repo: IRepository, private val id:Int): ViewModel() {

    private val _apiResourceForSmartPhoneDetailedInfo=repo.apiResourceForSmartPhoneDetailedInfo
    val apiResourceForSmartPhoneDetailedInfo: LiveData<ApiResource<GetSmartPhoneDetailedInfoResponse>> = _apiResourceForSmartPhoneDetailedInfo
    init {
        repo.getSmartPhoneDetailedInfo(id)
    }

}
class SmartPhoneDetailedInfoVMFactory(private val repo: IRepository, private val id:Int): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SmartPhoneDetailedInfoViewModel(repo,id) as T
    }
}