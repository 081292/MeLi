package com.example.meli.ui.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class MeLiBaseViewModel<VS : Any, A : Any> : MeLiBaseActionViewModel<A>() {

    private val _viewState: MutableLiveData<VS> by lazy {
        MutableLiveData<VS>()
    }

    val viewState: LiveData<VS>
        get() = _viewState

    val currentState: VS
        get() = viewState.value!!

    protected fun updateState(updated: VS) {
        _viewState.value = updated
    }

    protected fun postState(updated: VS) {
        _viewState.postValue(updated)
    }

    protected fun updateState(updateFunction: VS.() -> VS) =
        updateState(updateFunction(_viewState.value!!))

    protected fun postSate(updateFunction: VS.() -> VS) =
        postState(updateFunction(_viewState.value!!))
}
