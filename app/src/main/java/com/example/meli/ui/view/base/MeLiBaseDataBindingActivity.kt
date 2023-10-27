package com.example.meli.ui.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.meli.BR
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel

abstract class MeLiBaseDataBindingActivity<T : ViewDataBinding, U : MeLiBaseViewModel<*, *>> :
    MeLiBaseViewModelActivity<U>() {

    private var _binding: T? = null

    protected val binding: T
        get() = _binding as T

    @LayoutRes
    protected abstract fun layoutResId(): Int?

    protected open fun bindViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutResId()?.let {
            _binding = DataBindingUtil.setContentView(this, it)
            binding.lifecycleOwner = this
            bindViewModel()
        }
    }
}