package com.example.meli.feature.sites.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.meli.databinding.FragmentSitesBinding
import com.example.meli.ui.view.base.Inflater
import com.example.meli.ui.view.base.MeLiBaseDataBindingFragment
import com.example.meli.ui.viewmodel.base.observeActions
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class SitesFragment : MeLiBaseDataBindingFragment<FragmentSitesBinding, SitesViewModel>() {

    override fun bindingInflater(): Inflater<FragmentSitesBinding> = FragmentSitesBinding::inflate

    override fun viewModelClass(): KClass<SitesViewModel> = SitesViewModel::class

    override val viewModel: SitesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })

        viewModel.observeActions(lifecycleScope) { actions ->

        }
    }
}
