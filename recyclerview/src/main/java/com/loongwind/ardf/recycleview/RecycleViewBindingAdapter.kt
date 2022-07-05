package com.loongwind.ardf.recycleview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *Author: loongwind
 *Time: 2019-09-04
 *Description: Data binding Adapter with recycler view
 */
@BindingAdapter(value = ["data", "itemLayout", "itemClick","itemViewType", "itemEventHandler"], requireAll = false)
fun setData(
    recyclerView: RecyclerView,
    data: List<Any>?,
    @LayoutRes itemLayout: Int,
    listener: BaseBindingAdapter.OnItemClickListener<Any>?,
    itemViewTypeCreator: BaseBindingAdapter.ItemViewTypeCreator?,
    itemEventHandler: Any?
) {
    val adapter = recyclerView.adapter
    if (adapter == null) {
        val simpleBindingAdapter = DefaultBindingAdapter(itemLayout)
        simpleBindingAdapter.data = data
        simpleBindingAdapter.itemClickListener = listener
        simpleBindingAdapter.itemViewTypeCreator = itemViewTypeCreator
        simpleBindingAdapter.itemEventHandler = itemEventHandler
        recyclerView.adapter = simpleBindingAdapter
    } else if (adapter is BaseBindingAdapter<*, *>) {
        (adapter as BaseBindingAdapter<Any, ViewDataBinding>).data = data
        adapter.itemViewTypeCreator = itemViewTypeCreator
        adapter.itemClickListener = listener
        adapter.itemEventHandler = itemEventHandler
    }
}