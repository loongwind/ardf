package com.loongwind.ardf.base.utils

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.loongwind.ardf.base.BaseViewModel
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
            it is Class<*> && ViewDataBinding::class.java.isAssignableFrom(it)
        } as? Class<*>
    }
    return null
}