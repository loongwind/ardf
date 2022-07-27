package com.loongwind.ardf.demo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.loongwind.ardf.base.BaseBindingActivity
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.demo.databinding.TestPageBinding

//泛型类型是布局通过 DataBinding 自动生成的 ViewDataBinding 类型
class TestActivity : BaseBindingViewModelActivity<TestPageBinding, TestViewModel>() {

//    // 通过 binding 操作界面元素更新界面
//    override fun initDataBinding(binding: TestPageBinding) {
//        binding.text = "Hello ardf"
//    }


    // 接收事件
    override fun onEvent(eventId: Int) {
        super.onEvent(eventId)
        // 判断事件 id 并进行对应处理
        when(eventId){
            TestViewModel.EVENT_TO_DETAILS -> startActivity(Intent(this, DetailsActivity::class.java))
            TestViewModel.EVENT_SHOW_DIALOG -> {
                AlertDialog.Builder(this)
                    .setTitle("Dialog")
                    .setMessage("this is dialog")
                    .setNegativeButton("cancel") { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

    }
}