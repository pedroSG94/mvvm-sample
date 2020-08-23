package com.pedro.mvvm.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pedro.mvvm.model.User
import com.pedro.mvvm.task.ApiRestRepository
import com.pedro.mvvm.task.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(
    private val dataBaseRepository: DataBaseRepository,
    private val apiRestRepository: ApiRestRepository
) : ViewModel() {

    val usersObserver = MutableLiveData<List<User>>()
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
            withContext(Dispatchers.IO) {
                apiRestRepository.getUsers().forEach {
                    addUser(it.user, it.password)
                }
            }
        }
    }

    /**
     * add user manually
     */
    fun addUser(user: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataBaseRepository.insertUser(User(user, password))
            }
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