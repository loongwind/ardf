package com.loongwind.ardf.demo

import com.loongwind.ardf.base.BaseBindingActivity
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.demo.databinding.DetailsPageBinding
import com.loongwind.ardf.demo.databinding.TestPageBinding

//泛型类型是布局通过 DataBinding 自动生成的 ViewDataBinding 类型
class DetailsActivity : BaseBindingViewModelActivity<DetailsPageBinding, TestViewModel>() {

//    // 通过 binding 操作界面元素更新界面
//    override fun initDataBinding(binding: TestPageBinding) {
//        binding.text = "Hello ardf"
//    }
}