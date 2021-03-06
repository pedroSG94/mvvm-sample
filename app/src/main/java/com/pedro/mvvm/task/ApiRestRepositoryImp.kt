package com.pedro.mvvm.task

import com.pedro.mvvm.data.api.ApiRestImp
import com.pedro.mvvm.model.User
import com.pedro.mvvm.toUser

class ApiRestRepositoryImp(private val apiRest: ApiRestImp): ApiRestRepository {

    override suspend fun getUsers(): List<User> {
        val users = apiRest.getUsers()
        return users.map { it.toUser() }
    }
}