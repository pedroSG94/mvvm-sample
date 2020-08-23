package com.pedro.mvvm.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedro.mvvm.databinding.UserItemBinding
import com.pedro.mvvm.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val items = arrayListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addUsers(users: List<User>) {
        items.addAll(users)
    }

    fun clearUsers() {
        items.clear()
    }

    class ViewHolder(private val userItemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(userItemBinding.root) {

        fun bind(user: User) {
            userItemBinding.tvUser.text = user.user
            userItemBinding.tvPassword.text = user.password
        }
    }
}