package com.example.meli.ui.view.base

import androidx.fragment.app.Fragment

abstract class MeLiBaseFragment : Fragment() {

    /**
     * Method to be called whenever we want to navigate backwards.
     */
    protected fun navigateBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}