package com.example.projectoneecommerecemvvm.model.remote

import com.example.projectoneecommerecemvvm.model.data.GetLoginResponse
import com.example.projectoneecommerecemvvm.model.data.GetRegistrationResponse
import com.example.projectoneecommerecemvvm.model.data.User
import com.example.projectoneecommerecemvvm.model.data.UserLogin
import com.example.projectoneecommerecemvvm.model.data.UserRegisterInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-type: application/json")
    @POST("User/auth")
    fun userLogin(
        @Body userLogin: UserLogin
    ):Call<GetLoginResponse>

    @Headers("Content-type: application/json")
    @POST("User/register")
    fun userRegister(
        @Body userRegisterInfo:UserRegisterInfo
    ):Call<GetRegistrationResponse>

    companion object {
        private lateinit var apiService: ApiService
        fun getInstance(): ApiService {
            if(!::apiService.isInitialized) {
                apiService = ApiClient.retrofit.create(ApiService::class.java)
            }
            return apiService
        }
    }
}