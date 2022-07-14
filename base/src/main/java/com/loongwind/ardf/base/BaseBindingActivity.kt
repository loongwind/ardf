package com.loongwind.ardf.base


import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.loongwind.ardf.base.utils.getBindingType

/**
 * @Description: databinding BaseActivity
 * @Author: loongwind
 * @CreateDate： 2020/8/3 11:40 PM
 *
 */
abstract class BaseBindingActivity<BINDING :ViewDataBinding>:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = createDataBinding()
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initDataBinding(binding)
    }

    private fun createDataBinding(): BINDING {
        return getBindingType(javaClass)
            ?.getMethod("inflate", LayoutInflater::class.java)
            ?.invoke(null, LayoutInflater.from(this)) as BINDING
    }

    abstract fun initDataBinding(binding: BINDING)
}