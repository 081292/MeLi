package com.example.meli

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
import com.example.meli.ui.viewmodel.base.MeLiBaseActionViewModel
import com.example.meli.ui.viewmodel.base.observeActions
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.Rule
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible

abstract class MeLiBaseViewModelTest<A : Any, T : MeLiBaseActionViewModel<A>> {

    protected lateinit var viewModel: T
    private val actionObserver: (A) -> Unit = mockk(relaxed = true)

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var mainCoroutineRule = MeLiTestRule()

    /**
     * Instantiates the view model
     */
    abstract fun viewModelProvider(): T

    open fun setupViewModel() {
        viewModel = viewModelProvider()
    }

    /**
     * Verifies tha action that are called.
     */
    fun verifyActions(vararg a: A) {
        viewModel.observeActions(mainCoroutineRule, actionObserver)
        verifyOrder {
            a.forEach {
                actionObserver.invoke(it)
            }
        }
    }

    /**
     * Using reflection this class let uss call a private method in a class.
     */
    inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
        T::class
            .declaredMemberFunctions
            .firstOrNull { it.name == name }
            ?.apply { isAccessible = true }
            ?.call(this, *args)

    /**
     * Using reflection this class let uss call a suspended private method in a class.
     */
    suspend inline fun <reified T> T.callSuspendedPrivateFunc(
        name: String,
        vararg args: Any?
    ): Any? =
        T::class
            .declaredMemberFunctions
            .firstOrNull { it.name == name }
            ?.apply { isAccessible = true }
            ?.callSuspend(this, *args)

}