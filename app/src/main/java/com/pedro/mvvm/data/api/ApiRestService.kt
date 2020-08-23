package com.pedro.mvvm.data.api

import com.pedro.mvvm.data.api.models.ApiRestUser
import retrofit2.http.GET

interface ApiRestService {

    @GET("/users")
    suspend fun getUsers(): List<ApiRestUser>
}