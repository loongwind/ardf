package com.loongwind.ardf.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingAdapter<T:Any, BINDING : ViewDataBinding> :
    RecyclerView.Adapter<BindingViewHolder<T, BINDING>>() {

    var itemClickListener: OnItemClickListener<T>? = null
    private var listener: ObserverListChangeListener<T>? = null
    var itemViewTypeCreator: ItemViewTypeCreator? = null
    var itemEventHandler : Any? = null

    /**
     * 列表数据
     */
    var data: List<T>? = null
        @SuppressLint("NotifyDataSetChanged")
        set(data) {
            val oldData = field
            field = data
            // 判断如果是 ObservableList 类型，则为其添加 changeCallback 回调
            if (data is ObservableList<*>) {
                // 如果 listener 为空则创建 ObserverListChangeListener 对象，传入当前 Adapter
                if (listener == null || oldData != data) {
                    listener = ObserverListChangeListener(this)
                    notifyDataSetChanged()
                    (data as? ObservableList<T>)?.addOnListChangedCallback(listener)
                }
            }else{
                // 刷新界面数据
                notifyDataSetChanged()
            }

        }


    @get:LayoutRes
    abstract val layoutRes: Int

    fun getItem(position: Int): T? {
        return data?.getOrNull(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T, BINDING> {
        val layout = itemViewTypeCreator?.getItemLayout(viewType) ?: layoutRes
        val binding = DataBindingUtil.inflate<BINDING>(LayoutInflater.from(parent.context), layout, parent, false)
        val holder = BindingViewHolder<T, BINDING>(binding)
        bindClick(holder, binding)
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewTypeCreator?.getItemViewType(position, getItem(position))
            ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T, BINDING>, position: Int) {
        holder.bind(getItem(position))
        holder.setItemEventHandler(itemEventHandler)
        holder.setItemPosition(position)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    interface OnItemClickListener<T> {
        fun onItemClick(t: T?, position: Int)
    }


    protected fun bindClick(holder: BindingViewHolder<*, *>, binding: BINDING) {
        itemClickListener?.let {
            binding.root.setOnClickListener {
                val position = holder.layoutPosition
                itemClickListener?.onItemClick(getItem(position), position)
            }
        }
    }

    interface ItemViewTypeCreator{
        /**
         * 通过 item 下标和数据返回布局类型
         * @param position item 下标
         * @param item item 数据
         * @return item 布局类型
         */
        fun getItemViewType(position: Int, item: Any?) : Int

        /**
         * 通过 item 布局类型返回布局资源 id
         * @param viewType item 数据类型
         * @return item 布局资源 id
         */
        fun getItemLayout(viewType: Int) : Int
    }

}
