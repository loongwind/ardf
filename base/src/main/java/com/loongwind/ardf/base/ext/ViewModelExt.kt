package com.loongwind.ardf.base.ext


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.base.BaseBindingViewModelFragment
import com.loongwind.ardf.base.BaseViewModel

/**
 *
 * @description BaseViewModel 扩展方法，自动bind ViewModel 数据变化提示信息
 * observe owner 为继承 BaseBindingViewModelActivity 的 Activity
 * @param context
 * @return
 *
 */
fun BaseViewModel.bind(activity: BaseBindingViewModelActivity<*,*>) {
    observe(activity, activity){
        activity.onEvent(it)
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
fun BaseViewModel.bind(fragment: BaseBindingViewModelFragment<*, *>) {
    observe(fragment, fragment.context){
        fragment.onEvent(it)
    }
}


fun  BaseViewModel.observe( owner: LifecycleOwner, context: Context?, onEvent: (Int) -> Unit){
    hintText.observe(owner){
        val content = getHintText()
        if (!content.isNullOrBlank()) {
            context?.toast(content)
        }
    }
    hintTextRes.observe(owner) {
        val contentRes = getHintRes()
        if (contentRes > 0) {
            context?.toast(contentRes)
        }
    }

    event.observe(owner) {
        event.value?.getValueIfNotHandled()?.let {
            onEvent(it)
        }
    }
}