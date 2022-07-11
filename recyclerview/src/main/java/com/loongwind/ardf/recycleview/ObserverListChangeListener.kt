package com.loongwind.ardf.recycleview

import android.annotation.SuppressLint
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

/**
 *Author: loongwind
 *Time: 2019-07-26
 *Description: ObserverListChangeListener
 */
class ObserverListChangeListener<T>(private val adapter:  RecyclerView.Adapter<*>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onChanged(sender: ObservableList<T>) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        adapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        adapter.notifyItemRangeChanged(positionStart, itemCount)
    }

}