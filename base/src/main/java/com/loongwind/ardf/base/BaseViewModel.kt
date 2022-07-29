package com.loongwind.ardf.base

import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loongwind.ardf.base.event.EVENT_BACK
import com.loongwind.ardf.base.event.Event

/**
 * @Description: ViewModel 基类，定义数据加载状态（isLoading）、提示信息（hintText/hintTextRes）、
 * ViewModel 与视图之间的事件传递 ViewModelEvent
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:09 AM
 *
 */
open class BaseViewModel: ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    private var showLoadingCount = ObservableInt(0)
    // 提示文字
    var hintText = MutableLiveData<Event<String>>()
    // 提示文字资源
    var hintTextRes = MutableLiveData<Event<Int>>()

    // 事件
    var event = MutableLiveData<Event<Int>>()

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
        hintText.value = Event(msg)
    }

    protected fun postHintText(msgRes: Int) {
        hintTextRes.value = Event(msgRes)
    }

    protected fun postEvent(eventId: Int) {
        event.value = Event(eventId)
    }

    /**
     * 返回事件
     */
    fun back(){
        postEvent(EVENT_BACK)
    }

    private fun showLoading(){
        showLoadingCount.set(showLoadingCount.get() + 1)
    }

    private fun dismissLoading(){
        showLoadingCount.set(showLoadingCount.get() - 1)
    }
}