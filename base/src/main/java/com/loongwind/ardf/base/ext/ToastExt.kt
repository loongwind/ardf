package com.loongwind.ardf.base.ext

import android.content.Context
import android.widget.Toast

fun Context.toast(msg:String){
    if(msg.isNotBlank()){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Context.toast(msgRes:Int){
    if(msgRes > 0){
        Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show()
    }
}