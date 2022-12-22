package com.loongwind.ardf.demo

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.loongwind.ardf.base.BaseViewModel

class RecycleViewModel : BaseViewModel(){
    val data = ObservableArrayList<String>()
    val itemDecorations = listOf<ItemDecoration>(CustomItemDecoration(), CustomItemDecoration2())

    init {
        for (i in 0..5){
            data.add("Item $i")
            println(data)
        }
    }

    fun onItemClick(item:Any?){
        if(item is String){
            postHintText(item)
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