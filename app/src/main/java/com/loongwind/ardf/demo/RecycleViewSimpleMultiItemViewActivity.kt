package com.loongwind.ardf.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loongwind.ardf.demo.databinding.ActivityRecycleviewSimpleMultiItemViewBinding

class RecycleViewSimpleMultiItemViewActivity : AppCompatActivity(), IView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRecycleviewSimpleMultiItemViewBinding>(
            this,
            R.layout.activity_recycleview_simple_multi_item_view
        )

        binding.viewModel = MultiItemViewModel(this)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}