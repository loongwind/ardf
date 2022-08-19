package com.loongwind.ardf.net

import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.scope.Scope

/**
 *Author: chengminghui
 *Time: 2019-12-03
 *Description: xxx
 */

typealias ErrorHandle = (Throwable) -> Boolean
typealias CoroutineLambda = suspend CoroutineScope.() -> Unit


fun request(coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main), exceptionHandler: HttpExceptionHandler? = getHttpExceptionHandler(),
            error: ErrorHandle? = null,
            block: CoroutineLambda) {
    coroutineScope.launch {
        try {
            block()
        } catch (e: Exception) {
            e.printStackTrace()
            if (error?.invoke(e) != true) {
                exceptionHandler?.handleException(e)
            }
        }
    }
}

private fun getHttpExceptionHandler() = object : KoinComponent {
    fun getExceptionHandler(): HttpExceptionHandler {
        return get()
    }
}.getExceptionHandler()