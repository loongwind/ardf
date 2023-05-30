package com.loongwind.ardf

import androidx.lifecycle.viewModelScope
import com.loongwind.ardf.base.BaseViewModel
import com.loongwind.ardf.net.CoroutineLambda
import com.loongwind.ardf.net.ErrorHandle
import com.loongwind.ardf.net.request


fun BaseViewModel.launch(isShowLoading: Boolean = true,
                     onError: ErrorHandle? = null,
                     block: CoroutineLambda
){
        request(coroutineScope = this.viewModelScope, error = { t ->
            isLoading.value = false
            onError?.invoke(t) == true
        }) {
            if(isShowLoading){
                showLoading()
            }
            block()
            if(isShowLoading){
                dismissLoading()
            }
        }
}