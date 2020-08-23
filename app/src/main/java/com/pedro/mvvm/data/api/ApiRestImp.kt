package com.pedro.mvvm.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRestImp {

    private val baseURL = "https://jsonplaceholder.typicode.com"
    private val service = getRetrofit().create(ApiRestService::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getUsers() = service.getUsers()
}