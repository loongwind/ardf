package com.loongwind.ardf.base.utils

import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.loongwind.ardf.base.BaseViewModel
import org.koin.android.ext.android.getKoinScope
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.scope.Scope
import java.lang.reflect.ParameterizedType

/**
 *
 * @Description:
 * @Author: loongwind
 * @CreateDate：2020/8/13 6:07 AM
 *
 */
fun getViewModelType(clazz: Class<*>) : Class<out ViewModel>? {
    val superclass = clazz.genericSuperclass
    if (superclass is ParameterizedType) {
        //返回表示此类型实际类型参数的 Type 对象的数组
        val actualTypeArguments = superclass.actualTypeArguments
        //返回第一个符合条件的 Type 对象
        return actualTypeArguments.firstOrNull{
            it is Class<*> && BaseViewModel::class.java.isAssignableFrom(it)
        } as? Class<out ViewModel>
    }
    return null
}


fun getBindingType(clazz: Class<*>) : Class<*>? {
    val superclass = clazz.genericSuperclass
    if (superclass is ParameterizedType ) {
        //返回表示此类型实际类型参数的 Type 对象的数组
        val actualTypeArguments = superclass.actualTypeArguments
        return actualTypeArguments.firstOrNull {
            // 判断是 Class 类型 且是 ViewDataBinding 的子类
            it is Class<*> && ViewDataBinding::class.java.isAssignableFrom(it)
        } as? Class<*>
    }
    return null
}

@OptIn(KoinInternalApi::class)
fun ComponentActivity.injectViewModel() : ViewModel?{
    return getViewModel(javaClass, getKoinScope(), this, viewModelStore )

}

@OptIn(KoinInternalApi::class)
fun Fragment.injectViewModel() : ViewModel?{
    return getViewModel(javaClass, getKoinScope(), this, viewModelStore )

}

/**
 * @param javaClass Class类型
 * @param scope koin生命周期范围
 * @param owner ViewModelStoreOwner 类型，ViewModel 绑定什么周期对象，Activity、Fragment 都实现了该接口
 * @param viewModelStore 存储 ViewModel 的对象
 */
@OptIn(KoinInternalApi::class)
fun getViewModel(javaClass : Class<*>,
                 scope: Scope,
                 owner: ViewModelStoreOwner,
                 viewModelStore: ViewModelStore) : ViewModel?{
    // 获取当前 Activity 上 ViewModel 泛型的实际类型
    val viewModel = getViewModelType(javaClass)?.let {
        // 获取 ViewModelFactory
        val viewModelFactory = getViewModelFactory(owner, it.kotlin, null, null, null, scope)
        //获取注入的 ViewModel
        ViewModelLazy(it.kotlin, { viewModelStore }, { viewModelFactory} ).value
    }
    return viewModel
}