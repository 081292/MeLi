package com.example.meli.feature.items.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.meli.databinding.FragmentItemsBinding
import com.example.meli.ui.view.base.Inflater
import com.example.meli.ui.view.base.MeLiBaseDataBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class ItemsFragment : MeLiBaseDataBindingFragment<FragmentItemsBinding, ItemsViewModel>() {

    private val args: ItemsFragmentArgs by navArgs()

    override fun bindingInflater(): Inflater<FragmentItemsBinding> = FragmentItemsBinding::inflate

    override fun viewModelClass(): KClass<ItemsViewModel> = ItemsViewModel::class

    override val viewModel: ItemsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })

        //args.siteId
    }
}
