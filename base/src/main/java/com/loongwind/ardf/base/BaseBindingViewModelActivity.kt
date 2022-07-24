package com.loongwind.ardf.base


import androidx.databinding.ViewDataBinding
import com.loongwind.ardf.base.event.EVENT_BACK
import com.loongwind.ardf.base.event.OnEventListener
import com.loongwind.ardf.base.ext.bind
import com.loongwind.ardf.base.utils.injectViewModel

/**
 * @Description: Databinding + ViewModel BaseActivity
 * @Author: loongwind
 * @CreateDate： 2020/8/13 7:09 AM
 *
 */
open class BaseBindingViewModelActivity<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingActivity<BINDING>(), OnEventListener {



    //创建 ViewModel 变量并延迟初始化
    val viewModel:VM by lazy {
        createViewModel()
    }

    override fun initDataBinding(binding: BINDING) {
        //绑定 viewModel
        //绑定变量为 vm。
        // 具体业务实现中在实际的布局 xml 文件中声明当前视图的 ViewModel 变量为 vm 即可自动进行绑定。
        binding.setVariable(BR.vm,viewModel)

    }

    override fun  onEvent(eventId: Int) {
        if(eventId == EVENT_BACK){
            onBackPressed()
        }
    }

    /**
     * @description 初始化 ViewModel 并自动进行绑定
     * @return VM ViewModel 实例对象
     */
    private fun createViewModel():VM{
        try {
            //注入 ViewModel，并转换为 VM 类型
            val viewModel = injectViewModel() as VM
            viewModel.bind(this)
            return viewModel
        }catch (e:Exception){
            // 抛出异常
            throw Exception("ViewModel is not inject", e)
        }
    }
}
