package com.example.meli.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.meli.R
import com.example.meli.ui.view.base.MeLiBaseViewModelActivity
import com.example.meli.ui.viewmodel.MainActions
import com.example.meli.ui.viewmodel.MainViewModel
import com.example.meli.ui.viewmodel.base.observeActions
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : MeLiBaseViewModelActivity<MainViewModel>() {

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.observeActions(lifecycleScope) { actions ->
            when (actions) {
                is MainActions.NetworkConnectionEstablished -> onNetworkConnectionEstablished()
                is MainActions.NetworkConnectionLost -> onNetworkConnectionLost()
            }
        }
    }

    private fun onNetworkConnectionEstablished() {
        navController.navigate(R.id.sitesFragment)
    }

    private fun onNetworkConnectionLost() {
        navController.navigate(R.id.networkConnectionLostFragment)
    }
}