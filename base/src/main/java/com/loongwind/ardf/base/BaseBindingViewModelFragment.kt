package com.loongwind.ardf.base


import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import com.loongwind.ardf.base.event.OnEventListener
import com.loongwind.ardf.base.ext.bind
import com.loongwind.ardf.base.utils.getViewModel
import com.loongwind.ardf.base.utils.getViewModelType
import com.loongwind.ardf.base.utils.injectViewModel
import org.koin.android.ext.android.getKoinScope
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.scope.Scope
import java.lang.Exception

/**
 * @Description: Databinding + ViewModel BaseFragment
 * @Author: loongwind
 * @CreateDate： 2020/8/13 7:29 AM
 *
 */
open class BaseBindingViewModelFragment<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingFragment<BINDING>(), OnEventListener {
    val viewModel:VM by lazy {
        createViewModel()
    }

    override fun initDataBinding(binding: BINDING) {
        //RDF 默认自动绑定 vm。具体业务实现中在实际的视图 xml 文件中声明当前视图的 ViewModel 为
        // vm 即可自动进行绑定。
        binding.setVariable(BR.vm,viewModel)
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

        val scope : Scope?
        val owner: ViewModelStoreOwner?

        val activity = this.activity ?: throw Exception("Fragment Activity is null")
        if(isShareViewModel()){
            scope = activity.getKoinScope()
            owner =  activity
        }else{
            scope = getKoinScope()
            owner = this
        }
        try {
            val viewModel = getViewModel(javaClass, scope, owner, viewModelStore) as VM
            viewModel.bind(this)
            return viewModel
        } catch (e: Exception) {
            throw e
        }
    }

    override fun onEvent(eventId: Int) {
    }


    /**
     *
     * @description 是否保持 ViewModel。默认创建与当前 Fragment 生命周期绑定的 ViewModel。
     * 重写此方法返回 true，则创建与当前 Fragment 宿主 Activity 生命周期绑定的 ViewModel，与当前
     * Activity 绑定的其他 Fragment 可共享该 ViewMoel
     * @return true：保持 ViewModel 生命周期与宿主 Activity 同步，false：保持 ViewModel 与当前
     * Fragment 生命周期同步
     *
     */
    open fun isShareViewModel():Boolean{
        return false
    }
}