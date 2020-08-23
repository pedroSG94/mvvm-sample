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
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity

            rvUsers.adapter = adapter
            //return error from viewModel and show in screen
            mainViewModel.errorObserver.observe(this@MainActivity, Observer {
                toast(it)
            })
        }
    }
}