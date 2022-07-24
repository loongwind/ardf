package com.loongwind.ardf.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.loongwind.ardf.base.utils.getBindingType

/**
 * @Description:
 * @Author: loongwind
 * @CreateDate： 2020/8/4 11:46 PM
 *
 */
abstract class BaseBindingFragment<BINDING:ViewDataBinding>: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //创建 ViewDataBinding 实例
        val binding = createDataBinding(inflater, container)
        //绑定当前 Fragment 生命周期
        binding.lifecycleOwner = this

        // 初始化数据绑定
        initDataBinding(binding)
        //返回布局 View 对象
        return binding.root
    }

    /**
     * 根据泛型 BINDING 创建 ViewDataBinding 实例
     */
    private fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING {
        return getBindingType(javaClass)// 获取泛型类型
            ?.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java) // 反射获取 inflate 方法
            ?.invoke(null, inflater, container, false) as BINDING // 通过反射调用 inflate 方法
    }

    /**
     * 初始化数据绑定
     * 子类实现该方法通过 binding 绑定数据
     */
    abstract fun initDataBinding(binding: BINDING)

}