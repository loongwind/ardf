package com.loongwind.ardf.demo

import androidx.databinding.ObservableArrayList
import com.loongwind.ardf.recycleview.BaseBindingAdapter

class MultiItemViewModel(var view: IView){
    // List 的 item 数据类型改为 Any
    val data = ObservableArrayList<Any>()
    // 定义多 item 布局类型的创建器
    val itemViewTypes = object : BaseBindingAdapter.ItemViewTypeCreator{
        override fun getItemViewType(position: Int, item: Any?): Int {
            // 通过 item 数据类型返回不同的布局类型
            return if(item is String){
                0
            }else{
                1
            }
        }

        override fun getItemLayout(viewType: Int): Int {
            // 根据不同的布局类型返回不同的布局资源 id
            return if(viewType == 0){
                R.layout.layout_item
            }else{
                R.layout.layout_item2
            }
        }

    }
    init {
        // 添加测试数据
        for (i in 0..10){
            // 双数添加字符串数据，单数添加 User 数据
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