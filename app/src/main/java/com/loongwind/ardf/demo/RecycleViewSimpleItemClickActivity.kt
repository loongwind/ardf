package com.loongwind.ardf.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loongwind.ardf.demo.databinding.ActivityRecycleviewSimpleItemClickBinding

class RecycleViewSimpleItemClickActivity : AppCompatActivity(), IView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRecycleviewSimpleItemClickBinding>(
            this,
            R.layout.activity_recycleview_simple_item_click
        )

        binding.viewModel = RecycleViewModel(this)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}