package com.loongwind.ardf.base


import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import com.loongwind.ardf.base.utils.getBindingType

/**
 * @Description: databinding BaseActivity
 * @Author: loongwind
 * @CreateDate： 2020/8/3 11:40 PM
 *
 */
abstract class BaseBindingActivity<BINDING :ViewDataBinding>:BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //创建 ViewDataBinding 实例
        val binding = createDataBinding()
        //绑定当前 Activity 生命周期
        binding.lifecycleOwner = this
        //设置 View
        setContentView(binding.root)

        // 初始化数据绑定
        initDataBinding(binding)
    }

    /**
     * 根据泛型 BINDING 创建 ViewDataBinding 实例
     */
    private fun createDataBinding(): BINDING {
        return getBindingType(javaClass) // 获取泛型类型
            ?.getMethod("inflate", LayoutInflater::class.java) // 反射获取 inflate 方法
            ?.invoke(null, LayoutInflater.from(this)) as BINDING // 通过反射调用 inflate 方法
    }

    /**
     * 初始化数据绑定
     * 子类实现该方法通过 binding 绑定数据
     */
    abstract fun initDataBinding(binding: BINDING)
}