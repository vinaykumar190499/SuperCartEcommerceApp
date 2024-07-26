package com.example.projectoneecommerecemvvm.viewmodel.smartphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.IRepository
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneResponse
import com.example.projectoneecommerecemvvm.model.data.subcategory.GetSubCategoryResponse

class SmartPhoneListViewModel(private val repo: IRepository, private val id:Int): ViewModel() {

    private val _apiResourceForSmartPhonesList=repo.apiResourceForSmartPhone
    val apiResourceForSmartPhonesList: LiveData<ApiResource<GetSmartPhoneResponse>> = _apiResourceForSmartPhonesList
    init {
        repo.getSmartPhones(id)
    }

}
class SmartPhoneListVMFactory(private val repo: IRepository, private val id:Int): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SmartPhoneListViewModel(repo,id) as T
    }
}