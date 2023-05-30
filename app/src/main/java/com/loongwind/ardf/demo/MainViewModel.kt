package com.loongwind.ardf.demo

import com.loongwind.ardf.base.BaseViewModel
import com.loongwind.ardf.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : BaseViewModel() , KoinComponent{

    private val apiService:ApiService by inject()

    companion object {
        const val EVENT_SIMPLE = 0x00
        const val EVENT_ITEM_CLICK = 0x01
        const val EVENT_ITEM_EVENT = 0x02
        const val EVENT_MULTI_ITEM_VIEW = 0x03
        const val EVENT_TEST = 0x04
    }


    fun onClick(event : Int){
        postEvent(event)
    }

    fun getUser() = launch {
        val user = apiService.getUser()
        postHintText("------" + user.name + "------")
    }
}