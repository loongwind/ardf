package com.loongwind.ardf.demo

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.loongwind.ardf.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RecycleViewModel : BaseViewModel(){
    val data = ObservableArrayList<String>()
    val itemDecorations = listOf<ItemDecoration>(CustomItemDecoration(), CustomItemDecoration2())

    init {
        loadData()
    }

    private fun loadData() {
        val list = arrayListOf<String>()
        for (i in 0..20) {
            list.add("Item $i")
            println(list)
        }
        data.addAll(list)
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

    fun refreshData(){
        data.clear()
        loadData()
    }

}