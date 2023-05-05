package com.loongwind.ardf.base.ext


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.base.BaseBindingViewModelFragment
import com.loongwind.ardf.base.BaseViewModel
import com.loongwind.ardf.base.event.BackEvent
import com.loongwind.ardf.base.event.ToastRes
import com.loongwind.ardf.base.event.ToastString

/**
 *
 * @description BaseViewModel 扩展方法，自动bind ViewModel 数据变化提示信息
 * observe owner 为继承 BaseBindingViewModelActivity 的 Activity
 * @param context
 * @return
 *
 */
internal fun BaseViewModel.bind(activity: BaseBindingViewModelActivity<*,*>) {
    observe(activity){
        activity.onSubscribe(it)
    }
}

/**
 *
 * @description BaseViewModel 扩展方法，自动bind ViewModel 数据变化提示信息
 * observe owner 为继承 BaseBindingViewModelFragment 的 Fragment
 * @param context
 * @return
 *
 */
internal fun BaseViewModel.bind(fragment: BaseBindingViewModelFragment<*, *>) {
    observe(fragment){
        fragment.onSubscribe(it)
    }
}


private fun  BaseViewModel.observe( owner: LifecycleOwner, onEvent: (Any) -> Unit){
    // 订阅事件变化
    event.observe(owner) {
        event.value?.getValueIfNotHandled()?.let {
            onEvent(it)
        }
    }
}

internal fun BaseBindingViewModelActivity<*,*>.subscribe(event:Any) : Boolean{
    if(!subscribe(this, event){
            onEvent(it)
        }){
        return if(event is BackEvent){
            onBackPressed()
            true
        }else{
            false
        }
    }
    return true
}
internal fun BaseBindingViewModelFragment<*,*>.subscribe(event:Any) : Boolean{
    return subscribe(context, event){
        onEvent(it)
    }
}

private fun subscribe(context: Context?, event: Any, onEvent: (Int) -> Unit) : Boolean{
    when (event) {
        is Int -> {
            onEvent(event)
            return false
        }
        is ToastRes -> context?.toast(event.msgRes)
        is ToastString -> context?.toast(event.msg)
        else -> return false
    }
    return true
}