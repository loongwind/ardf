package com.loongwind.ardf.base


import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import com.loongwind.ardf.base.event.EVENT_BACK
import com.loongwind.ardf.base.event.OnEventListener
import com.loongwind.ardf.base.ext.bind
import com.loongwind.ardf.base.utils.getViewModelType
import org.koin.android.ext.android.getKoinScope
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory
import org.koin.core.annotation.KoinInternalApi

/**
 * @Description: Databinding + ViewModel BaseActivity
 * @Author: loongwind
 * @CreateDate： 2020/8/13 7:09 AM
 *
 */
open class BaseBindingViewModelActivity<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingActivity<BINDING>(), OnEventListener {



    val viewModel:VM by lazy {
        createViewModel()
    }

    override fun initDataBinding(binding: BINDING) {
        //RDF 默认自动绑定 vm。具体业务实现中在实际的视图 xml 文件中声明当前视图的 ViewModel 为
        // vm 即可自动进行绑定。
        binding.setVariable(BR.vm,viewModel)

    }

    override fun  onEvent(eventId: Int) {
        if(eventId == EVENT_BACK){
            onBackPressed()
        }
    }

    /**
     *
     * @description 初始化 ViewModel 并自动进行绑定
     * @param
     * @return VM
     *
     */
    @OptIn(KoinInternalApi::class)
    private fun createViewModel():VM{
        val viewModel = getViewModelType(javaClass)?.let {

            val scope = getKoinScope()
            val viewModelFactory = getViewModelFactory(this, it.kotlin, null, null, null, scope)
            ViewModelLazy(it.kotlin, { viewModelStore }, { viewModelFactory} ).value as VM
        }
        viewModel?.bind(this)
        return viewModel!!
    }
}
