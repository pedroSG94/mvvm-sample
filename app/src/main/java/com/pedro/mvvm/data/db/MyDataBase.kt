package com.pedro.mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedro.mvvm.data.db.dao.UserDao
import com.pedro.mvvm.data.db.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}