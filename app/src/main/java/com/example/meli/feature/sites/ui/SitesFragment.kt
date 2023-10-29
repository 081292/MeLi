package com.example.meli.feature.sites.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meli.databinding.FragmentSitesBinding
import com.example.meli.feature.sites.ui.adapter.SiteAdapter
import com.example.meli.ui.view.base.Inflater
import com.example.meli.ui.view.base.MeLiBaseDataBindingFragment
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

        viewModel.sendAction(SitesActions.FetchSites)
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is SitesState.Inactive -> {
                    Log.d("TAG", "Inactive")
                }

                is SitesState.Loading -> {
                    Log.d("TAG", "Loading")
                }

                is SitesState.SitesModelState -> {
                    initRecyclerView(it.siteUIModelList)
                }

                is SitesState.Network -> {
                    if(it.online){
                        Toast.makeText(
                            activity?.applicationContext,
                            "¡ Connected to Internet !",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            activity?.applicationContext,
                            "¡ No internet connection !",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                is SitesState.Error -> {
                    Log.d(
                        "TAG",
                        "Error{ Code: ${it.code.toString()} Message: ${it.error.toString()} }"
                    )
                }
            }
        }
    }

    private fun initRecyclerView(siteUIModelList: List<SiteUIModel>) {
        binding.sitesRV.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.sitesRV.adapter = SiteAdapter(siteUIModelList, findNavController())
    }
}
