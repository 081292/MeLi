package com.example.meli.ui.view.base

import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import kotlin.reflect.KClass

abstract class MeLiBaseViewModelFragment<T : MeLiBaseViewModel<*, *>> : MeLiBaseFragment() {

    protected abstract fun viewModelClass(): KClass<T>

    protected abstract val viewModel: T
}
