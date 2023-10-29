package com.example.meli.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.meli.BR
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel

typealias Inflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/**
 * Base class for Fragments that use Data Binding and a ViewModel
 */
abstract class MeLiBaseDataBindingFragment<T : ViewDataBinding, U : MeLiBaseViewModel<*, *>> :
    MeLiBaseViewModelFragment<U>() {

    private var _binding: T? = null

    protected val binding: T
        get() = _binding as T


    /**
     * The ViewDataBinding to be used by subclasses. This property is valid between [onCreateView] and
     * [onDestroyView] only, because the view exist only between those two methods
     */
    protected abstract fun bindingInflater(): Inflater<T>

    /**
     * Automatically tries to set view model of view binding.
     */
    protected open fun bindViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
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
