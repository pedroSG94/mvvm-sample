package com.pedro.mvvm.task

import androidx.lifecycle.Observer
import com.pedro.mvvm.model.User

interface DataBaseRepository {

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun getUsers(): List<User>

    suspend fun clearUsers()

    fun startObserveUsers(observerUser: Observer<List<User>>)

    fun stopObserveUsers()
}