package com.loongwind.ardf.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.demo.databinding.ActivityMainBinding

class MainActivity : BaseBindingViewModelActivity<ActivityMainBinding, MainViewModel>() {

    override fun onEvent(eventId: Int) {
        super.onEvent(eventId)
        when(eventId){
            MainViewModel.EVENT_SIMPLE -> startActivity(Intent(this, RecycleViewSimpleActivity::class.java))
            MainViewModel.EVENT_ITEM_CLICK -> startActivity(Intent(this, RecycleViewSimpleItemClickActivity::class.java))
            MainViewModel.EVENT_ITEM_EVENT -> startActivity(Intent(this, RecycleViewSimpleItemEventActivity::class.java))
            MainViewModel.EVENT_MULTI_ITEM_VIEW -> startActivity(Intent(this, RecycleViewSimpleMultiItemViewActivity::class.java))
        }

    }

}