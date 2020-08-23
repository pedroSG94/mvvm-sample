package com.pedro.mvvm.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pedro.mvvm.model.User
import com.pedro.mvvm.task.ApiRestRepository
import com.pedro.mvvm.task.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel @ViewModelInject constructor(
    private val dataBaseRepository: DataBaseRepository,
    private val apiRestRepository: ApiRestRepository
) : ViewModel() {

    val usersObserver = MutableLiveData<List<User>>()
    val errorObserver = MutableLiveData<String>()

    private val observer = Observer<List<User>> {
        usersObserver.value = it
    }

    init {
        dataBaseRepository.startObserveUsers(observer)
    }

    /**
     * add users from apiRest request
     */
    fun addOnlineUsers() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    apiRestRepository.getUsers().forEach {
                        addUser(it.user, it.password)
                    }
                    ""
                } catch (e: Exception) {
                    e.message ?: "unknown error"
                }
            }
            if (result.isNotEmpty()) errorObserver.value = result
        }
    }

    /**
     * add user manually
     */
    fun addUser(user: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                if (user.isNotEmpty() && password.isNotEmpty()) {
                    dataBaseRepository.insertUser(User(user, password))
                    true
                } else false
            }
            if (!result) errorObserver.value = "name or password is empty"
        }
    }

    /**
     * clear UserEntity table
     */
    fun clearUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataBaseRepository.clearUsers()
            }
        }
    }

    override fun onCleared() {
        dataBaseRepository.stopObserveUsers()
        super.onCleared()
    }
}