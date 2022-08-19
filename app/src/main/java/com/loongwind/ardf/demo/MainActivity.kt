package com.loongwind.ardf.demo

import android.content.Intent
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.base.ext.toast
import com.loongwind.ardf.demo.databinding.ActivityMainBinding
import com.loongwind.ardf.net.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : BaseBindingViewModelActivity<ActivityMainBinding, MainViewModel>() {

    val apiService : ApiService by inject()

    override fun onEvent(eventId: Int) {
        super.onEvent(eventId)
        when(eventId){
            MainViewModel.EVENT_SIMPLE -> startActivity(Intent(this, RecycleViewSimpleActivity::class.java))
            MainViewModel.EVENT_ITEM_CLICK -> startActivity(Intent(this, RecycleViewSimpleItemClickActivity::class.java))
            MainViewModel.EVENT_ITEM_EVENT -> startActivity(Intent(this, RecycleViewSimpleItemEventActivity::class.java))
            MainViewModel.EVENT_MULTI_ITEM_VIEW -> startActivity(Intent(this, RecycleViewSimpleMultiItemViewActivity::class.java))
            MainViewModel.EVENT_API -> requestApi()
        }

    }

    private fun requestApi(){
        request{
            val user = apiService.getUser()
            toast(user.name)
        }

    }

}