package com.example.meli.ui.viewmodel.base

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MeLiBaseActionViewModel<A : Any> : ViewModel() {

    private val actionChannel = Channel<A>(Channel.UNLIMITED)

    val actions: Flow<A>
        get() = actionChannel.receiveAsFlow()

    fun sendAction(action: A) {
        actionChannel.trySend(action)
    }
}

inline fun <A : Any> MeLiBaseActionViewModel<A>.observeActions(
    scope: LifecycleCoroutineScope,
    crossinline actionFunction: (A) -> Unit,
) {
    scope.launchWhenResumed {
        actions.collect {
            actionFunction(it)
        }
    }
}

inline fun <A : Any> MeLiBaseActionViewModel<A>.observeActions(
    scope: CoroutineScope,
    crossinline actionFunction: (A) -> Unit,
) {
    scope.launch {
        actions.collect {
            actionFunction(it)
        }
    }
}
