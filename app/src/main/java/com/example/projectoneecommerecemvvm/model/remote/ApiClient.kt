package com.example.projectoneecommerecemvvm.model.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val retrofit: Retrofit by lazy{
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply{
            addInterceptor(logInterceptor)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
        }.build()

        Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
    }
    val retrofitImg: Retrofit by lazy{
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply{
            addInterceptor(logInterceptor)
//            readTimeout(60, TimeUnit.SECONDS)
//            writeTimeout(60, TimeUnit.SECONDS)
//            connectTimeout(60, TimeUnit.SECONDS)
        }.build()

        Retrofit.Builder().apply {
            baseUrl(Constants.IMG_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
    }
}