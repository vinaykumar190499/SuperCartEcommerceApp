package com.example.projectoneecommerecemvvm.model

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectoneecommerecemvvm.common.ApiResource
import com.example.projectoneecommerecemvvm.model.data.dashboard.GetDashBoardResponse
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import com.example.projectoneecommerecemvvm.model.remote.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(val apiService: ApiService,

) :IRepository {
    override val _apiResource = MutableLiveData<ApiResource<GetDashBoardResponse>>()
    override val apiResource: LiveData<ApiResource<GetDashBoardResponse>> =_apiResource
    override fun getDashBoard() {
        val call: Call<GetDashBoardResponse> = apiService.getCategories()
        call.enqueue(object: Callback<GetDashBoardResponse> {
            override fun onResponse(
                call: Call<GetDashBoardResponse>,
                response: Response<GetDashBoardResponse>
            ) {
                if(!response.isSuccessful) {
                    val error = response.errorBody()?.string() ?: "Unknown server error. Please retry."
                    _apiResource.postValue(ApiResource.Error(error))
                    return
                }
                val searchResponse = response.body()
                if(searchResponse == null) {
                    _apiResource.postValue(ApiResource.Error("\"Empty response from server.\""))
                    return
                }
                _apiResource.postValue(ApiResource.Success(searchResponse))
            }

            override fun onFailure(p0: Call<GetDashBoardResponse>, p1: Throwable) {
                _apiResource.postValue(ApiResource.Error("\"something Went wrong.\""))
            }

        })
    }

}