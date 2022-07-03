package com.loongwind.ardf.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loongwind.ardf.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.recyclerviewSimple.setOnClickListener {
            startActivity(Intent(this, RecycleViewSimpleActivity::class.java))
        }
        binding.recyclerviewSimpleItemClick.setOnClickListener {
            startActivity(Intent(this, RecycleViewSimpleItemClickActivity::class.java))
        }
        binding.recyclerviewSimpleItemEvent.setOnClickListener {
            startActivity(Intent(this, RecycleViewSimpleItemEventActivity::class.java))
        }
        binding.recyclerviewSimpleMultiViewType.setOnClickListener {
            startActivity(Intent(this, RecycleViewSimpleMultiItemViewActivity::class.java))
        }
    }

}