package com.pedro.mvvm.task

import androidx.lifecycle.Observer
import com.pedro.mvvm.data.db.MyDataBase
import com.pedro.mvvm.data.db.entity.UserEntity
import com.pedro.mvvm.model.User
import com.pedro.mvvm.toUser
import com.pedro.mvvm.toUserEntity

class DataBaseRepository(private val db: MyDataBase) {

    private var observerUser: Observer<List<User>>? = null

    private var observer = Observer<List<UserEntity>> { users ->
        val result = users.map { it.toUser() }
        observerUser?.onChanged(result)
    }

    suspend fun insertUser(user: User) {
        val u = user.toUserEntity()
        db.userDao().insert(u)
    }

    suspend fun deleteUser(user: User) {
        val u = user.toUserEntity()
        db.userDao().delete(u)
    }

    suspend fun updateUser(user: User) {
        val u = user.toUserEntity()
        db.userDao().update(u)
    }

    suspend fun getUsers(): List<User> {
        val users = db.userDao().getAll()
        return users.map { it.toUser() }
    }

    suspend fun clearUsers() {
        db.userDao().clearTable()
    }

    fun startObserveUsers(observerUser: Observer<List<User>>) {
        this.observerUser = observerUser
        db.userDao().observeAll().observeForever(observer)
    }

    fun stopObserveUsers() {
        db.userDao().observeAll().removeObserver(this.observer)
        observerUser = null
    }
}