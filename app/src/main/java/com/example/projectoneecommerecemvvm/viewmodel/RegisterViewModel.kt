package com.example.projectoneecommerecemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectoneecommerecemvvm.model.data.GetRegistrationResponse
import com.example.projectoneecommerecemvvm.model.data.UserRegisterInfo
import com.example.projectoneecommerecemvvm.model.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel():ViewModel() {

    private val apiService: ApiService = ApiService.getInstance()
    val responseResult = MutableLiveData<GetRegistrationResponse>()
    val error = MutableLiveData<String>()

    fun registerUser(userRegisterInfo:UserRegisterInfo){
        val call: Call<GetRegistrationResponse> = apiService.userRegister(userRegisterInfo)
        call.enqueue(object:Callback<GetRegistrationResponse>{
            override fun onResponse(
                call: Call<GetRegistrationResponse>,
                response: Response<GetRegistrationResponse>
            ) {
                if(!response.isSuccessful) {
                    error.postValue(response.errorBody()?.string() ?: "Unknown server error")
                    return
                }
                val registerResponse:GetRegistrationResponse? = response.body()
                if(registerResponse == null) {
                    error.postValue("Empty result from server. Please retry.")
                    return
                }
                if(registerResponse.status == null || registerResponse.message.isEmpty()) {
                    error.postValue("No search results found")
                    return
                }
                responseResult.postValue(registerResponse)
            }

            override fun onFailure(call: Call<GetRegistrationResponse>, t: Throwable) {
                error.postValue("Failed to load result. Error is : $t")
            }

        })
    }
}