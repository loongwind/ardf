package com.loongwind.ardf.base.ext

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 *Author: chengminghui
 *Time: 2019-09-04
 *Description: xxx
 */

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.GONE
}


@BindingAdapter("android:src")
fun setImage(imageView: ImageView, imgRes: Int) {
    imageView.setImageResource(imgRes)
}