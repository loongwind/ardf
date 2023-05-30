package com.loongwind.ardf.base

import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loongwind.ardf.base.event.BackEvent
import com.loongwind.ardf.base.event.Event
import com.loongwind.ardf.base.event.ToastRes
import com.loongwind.ardf.base.event.ToastString

/**
 * @Description: ViewModel 基类，定义数据加载状态（isLoading）、提示信息（hintText/hintTextRes）、
 * ViewModel 与视图之间的事件传递 ViewModelEvent
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:09 AM
 *
 */
open class BaseViewModel: ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    private var showLoadingCount = ObservableInt(0)
    // 事件
    internal val event = MutableLiveData<Event<*>>()

    init {
        showLoadingCount.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val isShowLoading = showLoadingCount.get() > 0
                if(isShowLoading != isLoading.value){
                    isLoading.value = isShowLoading
                }
            }
        })
    }

    protected fun postHintText(msg: String) {
        postEvent(ToastString(msg))
    }

    protected fun postHintText(msgRes: Int) {
//        hintTextRes.value =
        postEvent(ToastRes(msgRes))
    }

    protected fun postEvent(content: Any) {
        event.value = Event(content)
    }

    /**
     * 返回事件
     */
    fun back(){
        postEvent(BackEvent())
    }

    fun showLoading(){
        showLoadingCount.set(showLoadingCount.get() + 1)
    }

    fun dismissLoading(){
        showLoadingCount.set(showLoadingCount.get() - 1)
    }

}