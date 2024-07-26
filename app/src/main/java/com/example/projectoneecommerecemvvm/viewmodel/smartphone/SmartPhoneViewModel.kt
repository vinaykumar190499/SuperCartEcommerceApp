package com.example.projectoneecommerecemvvm.viewmodel.smartphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.IRepository
import com.example.projectoneecommerecemvvm.model.data.dashboard.GetDashBoardResponse
import com.example.projectoneecommerecemvvm.model.data.subcategory.GetSubCategoryResponse

class SmartPhoneViewModel(private val repo: IRepository, private val id:Int): ViewModel() {

    private val _apiResource=repo.apiResourceForSubCategory
    val apiResource: LiveData<ApiResource<GetSubCategoryResponse>> = _apiResource
    init {
        repo.getSmartPhoneSubCategory(id)
    }

}
class SmartPhoneVMFactory(private val repo: IRepository,private val id:Int): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SmartPhoneViewModel(repo,id) as T
    }
}