package com.loongwind.ardf.demo

import com.loongwind.ardf.base.BaseViewModel

data class ShowDialogEvent(val title:String, val content:String)

class TestViewModel : BaseViewModel(){
    val text = "Hello ardf ViewModel"

    fun showToastString(){
        //传入字符串
        postHintText("Hello ardf toast")
    }

    fun showToastStringRes(){
        //传入字符串资源
        postHintText(R.string.hello)
    }

    fun goBack(){
        //调用父类提供的 back 方法
        back()
    }

    companion object {
        // 定义跳转到详情页的事件 id
        const val EVENT_TO_DETAILS = 0x00
        // 定义弹出 Dialog 的事件 id
        const val EVENT_SHOW_DIALOG = 0x01
    }

    fun toDetailsPage(){
        // 发送跳转详情页事件
        postEvent(EVENT_TO_DETAILS)
    }

    fun showDialog(){
        // 发送弹出 Dialog 事件
//        postEvent(EVENT_SHOW_DIALOG)
        postEvent(ShowDialogEvent("Dialog", "this is dialog event"))
    }
}