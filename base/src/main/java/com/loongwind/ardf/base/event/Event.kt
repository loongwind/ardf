package com.loongwind.ardf.base.event


/**
 * @Description: ViewModle 事件 Modle
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:27 AM
 *
 */
internal class Event<T>(private val value: T) {

    //是否已被处理
    private var handled = false

    /**
     * @description 防止粘性事件被多次消费，多个观察者场景下，只会被一个观察者消费
     */
    fun getValueIfNotHandled(): T? {
        return if (handled) {
            // 已处理返回 null
            null
        } else {
            // 标记为已处理
            handled = true
            value
        }
    }

    fun get(): T {
        return value
    }
}
//页面返回事件
const val EVENT_BACK = 0xff
//点击事件
const val EVENT_CLICK = 0x100
//列表 item 点击事件
const val EVENT_ITEM_CLICK = 0x101

internal class BackEvent
internal class ToastRes(val msgRes : Int)
internal class ToastString(val msg : String)