package com.example.meli.ui.view.base

import androidx.fragment.app.Fragment

abstract class MeLiBaseFragment: Fragment() {

    protected fun navigateBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}
