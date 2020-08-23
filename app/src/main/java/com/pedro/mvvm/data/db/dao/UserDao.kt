package com.pedro.mvvm.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pedro.mvvm.data.db.entity.UserEntity

/**
 * Suspend to use in coroutine like a suspend process like withContext() { }
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun observeAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE user = :user")
    suspend fun getByUser(user: String): List<UserEntity>

    @Query("DELETE FROM UserEntity")
    suspend fun clearTable()

    @Insert
    suspend fun insert(userEntity: UserEntity): Long

    @Update
    suspend fun update(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)
}