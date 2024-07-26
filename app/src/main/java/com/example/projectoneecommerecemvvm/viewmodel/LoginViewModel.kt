package com.example.projectoneecommerecemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectoneecommerecemvvm.model.data.GetLoginResponse
import com.example.projectoneecommerecemvvm.model.data.GetRegistrationResponse
import com.example.projectoneecommerecemvvm.model.data.UserLogin
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val apiService: ApiService = ApiService.getInstance()
    val loginResponseResult = MutableLiveData<GetLoginResponse>()
    val error = MutableLiveData<String>()
    fun userLogin(userLoginInfo:UserLogin){
        val call: Call<GetLoginResponse> = apiService.userLogin(userLoginInfo)
        call.enqueue(object: Callback<GetLoginResponse> {
            override fun onResponse(call: Call<GetLoginResponse>, response: Response<GetLoginResponse>) {
                if(!response.isSuccessful) {
                    error.postValue(response.errorBody()?.string() ?: "Unknown server error")
                    return
                }
                val loginResponse:GetLoginResponse? = response.body()
                if(loginResponse == null) {
                    error.postValue("Empty result from server. Please retry.")
                    return
                }
                if(loginResponse.status == null || loginResponse.message.isEmpty()) {
                    error.postValue("No search results found")
                    return
                }
                loginResponseResult.postValue(loginResponse)
            }

            override fun onFailure(call: Call<GetLoginResponse>, t: Throwable) {
                error.postValue("Failed to load result. Error is : $t")
            }

        })
    }
}