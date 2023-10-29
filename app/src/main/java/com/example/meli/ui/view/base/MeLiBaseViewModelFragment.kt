package com.example.meli.ui.view.base

import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import kotlin.reflect.KClass

/**
 * Base class for all Fragments that use [ViewModel]
 * Automatically provides a ViewModel instance for subclasses
 */
abstract class MeLiBaseViewModelFragment<T : MeLiBaseViewModel<*, *>> : MeLiBaseFragment() {

    /**
     * Class of [ViewModel] to be used
     */
    protected abstract fun viewModelClass(): KClass<T>

    protected abstract val viewModel: T
}