package com.example.meli.ui.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import kotlin.reflect.KClass

/**
 * Base activity for all activities in the MeLi app that use ViewModel
 */
abstract class MeLiBaseViewModelActivity<T : MeLiBaseViewModel<*, *>> : AppCompatActivity() {

    protected abstract fun viewModelClass(): KClass<T>

    protected abstract val viewModel: T
}