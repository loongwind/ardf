package com.loongwind.ardf.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loongwind.ardf.demo.databinding.ActivityRecycleviewSimpleItemEventBinding

class RecycleViewSimpleItemEventActivity : AppCompatActivity(), IView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRecycleviewSimpleItemEventBinding>(
            this,
            R.layout.activity_recycleview_simple_item_event
        )

        binding.viewModel = RecycleViewModel(this)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}