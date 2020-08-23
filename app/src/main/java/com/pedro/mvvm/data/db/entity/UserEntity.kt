package com.pedro.mvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val user: String,
    val password: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)