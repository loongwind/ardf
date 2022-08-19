package com.loongwind.ardf.net

import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 *Author: chengminghui
 *Time: 2019-12-03
 *Description: xxx
 */

typealias ErrorHandle = (Throwable) -> Boolean
typealias CoroutineLambda = suspend CoroutineScope.() -> Unit


private fun request(block: CoroutineLambda) {
    val exceptionHandle = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }
    GlobalScope.launch(exceptionHandle) {
        withContext(Dispatchers.Main) {
            block()
        }
    }


}

fun request(exceptionHandler: HttpExceptionHandler? = getHttpExceptionHandler(),
            error: ErrorHandle? = null,
            block: CoroutineLambda) {
    request {
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
