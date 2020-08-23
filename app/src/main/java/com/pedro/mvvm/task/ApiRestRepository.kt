package com.pedro.mvvm.task

import com.pedro.mvvm.model.User

interface ApiRestRepository {

    suspend fun getUsers(): List<User>
}