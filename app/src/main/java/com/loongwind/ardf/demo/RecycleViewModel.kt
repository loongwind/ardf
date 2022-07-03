package com.loongwind.ardf.demo

import androidx.databinding.ObservableArrayList

class RecycleViewModel(var view: IView){
    val data = ObservableArrayList<String>()

    init {
        for (i in 0..5){
            data.add("Item $i")
            println(data)
        }
    }

    fun onItemClick(item:Any?){
        if(item is String){
            view.toast(item)
        }
    }

    fun addItem(){
        data.add("Item ${data.size}")
    }

    fun deleteItem(){
        data.removeAt(data.size - 1)
    }

    fun eventDeleteItem(item:String){
        data.remove(item)
    }
}