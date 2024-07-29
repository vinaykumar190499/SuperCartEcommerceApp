package com.example.projectoneecommerecemvvm.model

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.data.dashboard.GetDashBoardResponse
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneDetailedInfoResponse
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneResponse
import com.example.projectoneecommerecemvvm.model.data.subcategory.GetSubCategoryResponse
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import com.example.projectoneecommerecemvvm.model.remote.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(val apiService: ApiService,

) :IRepository {
    override val _apiResourceForDashBoard = MutableLiveData<ApiResource<GetDashBoardResponse>>()
    override val apiResourceForDashBoard: LiveData<ApiResource<GetDashBoardResponse>> =_apiResourceForDashBoard
    override val _apiResourceForSubCategory= MutableLiveData<ApiResource<GetSubCategoryResponse>>()
    override val apiResourceForSubCategory: LiveData<ApiResource<GetSubCategoryResponse>> = _apiResourceForSubCategory
    override val _apiResourceForSmartPhone= MutableLiveData<ApiResource<GetSmartPhoneResponse>>()
    override val apiResourceForSmartPhone: LiveData<ApiResource<GetSmartPhoneResponse>> = _apiResourceForSmartPhone
    override val _apiResourceForSmartPhoneDetailedInfo = MutableLiveData<ApiResource<GetSmartPhoneDetailedInfoResponse>>()
    override val apiResourceForSmartPhoneDetailedInfo: LiveData<ApiResource<GetSmartPhoneDetailedInfoResponse>> = _apiResourceForSmartPhoneDetailedInfo

    override fun getDashBoard() {
        val call: Call<GetDashBoardResponse> = apiService.getCategories()
        call.enqueue(object: Callback<GetDashBoardResponse> {
            override fun onResponse(
                call: Call<GetDashBoardResponse>,
                response: Response<GetDashBoardResponse>
            ) {
                if(!response.isSuccessful) {
                    val error = response.errorBody()?.string() ?: "Unknown server error. Please retry."
                    _apiResourceForDashBoard.postValue(ApiResource.Error(error))
                    return
                }
                val searchResponse = response.body()
                if(searchResponse == null) {
                    _apiResourceForDashBoard.postValue(ApiResource.Error("\"Empty response from server.\""))
                    return
                }
                _apiResourceForDashBoard.postValue(ApiResource.Success(searchResponse))
            }

            override fun onFailure(p0: Call<GetDashBoardResponse>, p1: Throwable) {
                _apiResourceForDashBoard.postValue(ApiResource.Error("\"something Went wrong.\""))
            }

        })
    }

    override fun getSmartPhoneSubCategory(id:Int) {
        val call: Call<GetSubCategoryResponse> = apiService.getPhoneSubCategories(id)
        call.enqueue(object: Callback<GetSubCategoryResponse> {
            override fun onResponse(
                call: Call<GetSubCategoryResponse>,
                response: Response<GetSubCategoryResponse>
            ) {
                if(!response.isSuccessful) {
                    val error = response.errorBody()?.string() ?: "Unknown server error. Please retry."
                    _apiResourceForSubCategory.postValue(ApiResource.Error(error))
                    return
                }
                val searchResponse = response.body()
                if(searchResponse == null) {
                    _apiResourceForSubCategory.postValue(ApiResource.Error("\"Empty response from server.\""))
                    return
                }
                _apiResourceForSubCategory.postValue(ApiResource.Success(searchResponse))
            }

            override fun onFailure(p0: Call<GetSubCategoryResponse>, p1: Throwable) {
                _apiResourceForSubCategory.postValue(ApiResource.Error("\"something Went wrong.\""))
            }

        })
    }

    override fun getSmartPhones(id: Int) {
        val call: Call<GetSmartPhoneResponse> = apiService.getSmartPhones(id)
        call.enqueue(object: Callback<GetSmartPhoneResponse> {
            override fun onResponse(
                call: Call<GetSmartPhoneResponse>,
                response: Response<GetSmartPhoneResponse>
            ) {
                if(!response.isSuccessful) {
                    val error = response.errorBody()?.string() ?: "Unknown server error. Please retry."
                    _apiResourceForSmartPhone.postValue(ApiResource.Error(error))
                    return
                }
                val searchResponse = response.body()
                if(searchResponse == null) {
                    _apiResourceForSmartPhone.postValue(ApiResource.Error("\"Empty response from server.\""))
                    return
                }
                _apiResourceForSmartPhone.postValue(ApiResource.Success(searchResponse))
            }

            override fun onFailure(p0: Call<GetSmartPhoneResponse>, p1: Throwable) {
                _apiResourceForSmartPhone.postValue(ApiResource.Error("\"something Went wrong.\""))
            }

        })
    }

    override fun getSmartPhoneDetailedInfo(id: Int) {
        val call: Call<GetSmartPhoneDetailedInfoResponse> = apiService.getSmartPhonesDetailedInfo(id)
        call.enqueue(object: Callback<GetSmartPhoneDetailedInfoResponse> {
            override fun onResponse(
                call: Call<GetSmartPhoneDetailedInfoResponse>,
                response: Response<GetSmartPhoneDetailedInfoResponse>
            ) {
                if(!response.isSuccessful) {
                    val error = response.errorBody()?.string() ?: "Unknown server error. Please retry."
                    _apiResourceForSmartPhoneDetailedInfo.postValue(ApiResource.Error(error))
                    return
                }
                val searchResponse = response.body()
                if(searchResponse == null) {
                    _apiResourceForSmartPhoneDetailedInfo.postValue(ApiResource.Error("\"Empty response from server.\""))
                    return
                }
                _apiResourceForSmartPhoneDetailedInfo.postValue(ApiResource.Success(searchResponse))
            }

            override fun onFailure(p0: Call<GetSmartPhoneDetailedInfoResponse>, p1: Throwable) {
                _apiResourceForSmartPhoneDetailedInfo.postValue(ApiResource.Error("\"something Went wrong.\""))
            }

        })
    }

}