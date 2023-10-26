package com.example.meli.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel

typealias Inflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class MeLiBaseViewDataBindingFragment<T : ViewDataBinding, U : MeLiBaseViewModel<*, *>> :
    MeLiBaseViewModelFragment<U>() {

    private var _binding: T? = null

    protected val binding: T
        get() = _binding as T

    protected abstract fun bindingInflater(): Inflater<T>

    protected open fun bindViewModel() {
        binding.setVariable(3, viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = bindingInflater()(inflater, container, false)
        }

        binding.lifecycleOwner = this
        bindViewModel()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
