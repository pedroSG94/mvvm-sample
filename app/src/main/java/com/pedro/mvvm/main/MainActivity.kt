package com.pedro.mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.pedro.mvvm.databinding.ActivityMainBinding
import com.pedro.mvvm.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject @Named("apikey") lateinit var apikey: String

    private val mainViewModel: MainViewModel by viewModels()
    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            rvUsers.adapter = adapter
            mainViewModel.usersObserver.observe(this@MainActivity, Observer {
                if (it.isEmpty()) adapter.clearUsers()
                adapter.addUsers(it)
                adapter.notifyDataSetChanged()
            })

            bAddUser.setOnClickListener {
                if (etName.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                    mainViewModel.addUser(etName.text.toString(), etPassword.text.toString())
                } else {
                    toast("Name or password is empty")
                }
            }

            bRemoteUsers.setOnClickListener {
                mainViewModel.addOnlineUsers()
            }

            bclearUsers.setOnClickListener {
                mainViewModel.clearUsers()
            }
        }
    }
}