package com.pedro.mvvm

import android.app.Activity
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pedro.mvvm.data.api.models.ApiRestUser
import com.pedro.mvvm.data.db.entity.UserEntity
import com.pedro.mvvm.main.UsersAdapter
import com.pedro.mvvm.model.User

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Mappers
 */
fun UserEntity.toUser(): User = User(this.user, this.password, this.id)

fun User.toUserEntity(): UserEntity = UserEntity(this.user, this.password)

fun User.toApiRestUser(): ApiRestUser = ApiRestUser(this.user, this.password)

fun ApiRestUser.toUser(): User = User(this.name, this.email)

/**
 * For customs binding use a fun with @BindingAdapter("items")
 */