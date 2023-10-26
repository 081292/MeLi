package com.example.meli.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.meli.R
import com.example.meli.ui.view.base.MeLiBaseViewModelActivity
import com.example.meli.ui.viewmodel.SplashActions
import com.example.meli.ui.viewmodel.SplashViewModel
import com.example.meli.ui.viewmodel.base.observeActions
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class SplashActivity : MeLiBaseViewModelActivity<SplashViewModel>() {

    override fun viewModelClass(): KClass<SplashViewModel> = SplashViewModel::class

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.observeActions(lifecycleScope) { actions ->
            when (actions) {
                is SplashActions.NetworkSetupCompleted -> onNetworkSetupCompleted()
            }
        }
    }

    private fun onNetworkSetupCompleted() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
