package com.loongwind.ardf.demo

import androidx.databinding.ObservableArrayList
import com.loongwind.ardf.recycleview.BaseBindingAdapter

class MultiItemViewModel(var view: IView){
    val data = ObservableArrayList<Any>()
    val itemViewTypes = object : BaseBindingAdapter.ItemViewTypeCreator{
        override fun getItemViewType(position: Int, item: Any?): Int {
            return if(item is String){
                0
            }else{
                1
            }
        }

        override fun getItemLayout(viewType: Int): Int {
            return if(viewType == 0){
                R.layout.layout_item
            }else{
                R.layout.layout_item2
            }
        }

    }
    init {

        for (i in 0..10){
            if(i % 2 == 0){
                data.add("Item $i")
            }else{
                data.add(User(name = "Name $i", img = "https://picsum.photos/200"))
            }

            println(data)
        }
    }

    fun onItemClick(item:Any?){
        if(item is String){
            view.toast(item)
        }
        if(item is User){
            view.toast(item.name)
        }
    }

    fun addItem(){
        data.add("Item ${data.size}")
    }

    fun deleteItem(){
        data.removeAt(data.size - 1)
    }

    fun omImgClick(user: User){
        view.toast("Click Image ${user.name}")
    }
}