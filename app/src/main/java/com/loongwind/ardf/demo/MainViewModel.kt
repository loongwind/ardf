package com.loongwind.ardf.demo

import com.loongwind.ardf.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    companion object {
        const val EVENT_SIMPLE = 0x00
        const val EVENT_ITEM_CLICK = 0x01
        const val EVENT_ITEM_EVENT = 0x02
        const val EVENT_MULTI_ITEM_VIEW = 0x03
        const val EVENT_API = 0x04
    }


    fun onClick(event : Int){
        postEvent(event)
    }
}