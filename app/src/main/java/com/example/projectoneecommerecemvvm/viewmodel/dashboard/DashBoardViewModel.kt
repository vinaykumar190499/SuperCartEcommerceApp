package com.example.projectoneecommerecemvvm.viewmodel.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.IRepository
import com.example.projectoneecommerecemvvm.model.data.dashboard.GetDashBoardResponse

class DashBoardViewModel(private val repo:IRepository): ViewModel() {

    private val _apiResource=repo.apiResource
    val apiResource: LiveData<ApiResource<GetDashBoardResponse>> = _apiResource
    init {
        repo.getDashBoard()
    }

}
class DashBoardVMFactory(private val repo: IRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashBoardViewModel(repo) as T
    }
}