package com.example.projectoneecommerecemvvm.common

sealed class ApiResource<T> {
    class Success<T>(val result: T): ApiResource<T>()
    class Error<T>(val error: String): ApiResource<T>()
    class Loading<T>(): ApiResource<T>()
}