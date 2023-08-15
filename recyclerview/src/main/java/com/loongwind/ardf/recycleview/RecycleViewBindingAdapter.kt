package com.loongwind.ardf.recycleview

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.InsetDrawable
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.LayoutParams

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
        simpleBindingAdapter.setHasStableIds(true)
        recyclerView.adapter = simpleBindingAdapter
    } else if (adapter is BaseBindingAdapter<*, *>) {
        (adapter as BaseBindingAdapter<Any, ViewDataBinding>).data = data
        adapter.itemViewTypeCreator = itemViewTypeCreator
        adapter.itemClickListener = listener
        adapter.itemEventHandler = itemEventHandler
    }
}

private fun handItemDecoration(
    recyclerView: RecyclerView,
    itemDecorations: List<ItemDecoration>?
) {
    if(itemDecorations == null){
        return
    }
    // get all item decoration
    val oldItemDecorations = arrayListOf<ItemDecoration>()
    for (i in 0 until recyclerView.itemDecorationCount) {
        oldItemDecorations.add(recyclerView.getItemDecorationAt(i))
    }
    // remove old item decoration
    oldItemDecorations.forEach {
        recyclerView.removeItemDecoration(it)
    }

    // add new item decoration
    itemDecorations.forEach {
        recyclerView.addItemDecoration(it)
    }
}


@BindingAdapter(value = ["dividerOrientation", "dividerSize", "dividerColor","dividerLeftPadding", "dividerRightPadding", "dividerTopPadding", "dividerBottomPadding", "itemDecorations"], requireAll = false)
fun setDivider(
    recyclerView: RecyclerView,
    dividerOrientation: Int?,
    dividerSize: Float?,
    dividerColor: Int?,
    dividerLeftPadding: Float?,
    dividerRightPadding: Float?,
    dividerTopPadding: Float?,
    dividerBottomPadding: Float?,
    itemDecorations: List<ItemDecoration>?
) {
    if(dividerOrientation != null
        || dividerSize != null
        || dividerColor != null
        || dividerLeftPadding != null
        || dividerRightPadding != null
        || dividerTopPadding != null
        || dividerBottomPadding != null){
        val orientation = dividerOrientation ?: DividerItemDecoration.VERTICAL
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, orientation)
        var insetDrawable = recyclerView.context.resources.getDrawable(R.drawable.ardf_recyclerview_divider, null).mutate()
        if(insetDrawable is InsetDrawable){
            val shapeDrawable = insetDrawable.drawable
            if(shapeDrawable is GradientDrawable){
                shapeDrawable.setColor(dividerColor ?: recyclerView.context.resources.getColor(R.color.ardf_recyclerview_divider_color))
                val width = if (orientation == DividerItemDecoration.VERTICAL) LayoutParams.MATCH_PARENT else dividerSize ?: recyclerView.context.resources.getDimensionPixelSize(R.dimen.ardf_recyclerview_divider_size)
                val height = if (orientation == DividerItemDecoration.HORIZONTAL) LayoutParams.MATCH_PARENT else dividerSize ?: recyclerView.context.resources.getDimensionPixelSize(R.dimen.ardf_recyclerview_divider_size)
                shapeDrawable.setSize(width.toInt(), height.toInt())
            }
            insetDrawable = InsetDrawable(shapeDrawable, dividerLeftPadding?.toInt() ?: 0, dividerTopPadding?.toInt() ?:0 ,dividerRightPadding?.toInt() ?: 0, dividerBottomPadding?.toInt() ?: 0)
        }
        dividerItemDecoration.setDrawable(insetDrawable)
        if(itemDecorations == null){
            recyclerView.addItemDecoration(dividerItemDecoration)
        }else{
            val list = itemDecorations.toMutableList()
            list.add(dividerItemDecoration)
            handItemDecoration(recyclerView, list)
        }
    }else{
        handItemDecoration(recyclerView, itemDecorations)
    }
}
