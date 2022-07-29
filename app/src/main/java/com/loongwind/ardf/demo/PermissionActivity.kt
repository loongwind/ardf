package com.loongwind.ardf.demo

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.loongwind.ardf.base.BaseBindingActivity
import com.loongwind.ardf.base.BaseBindingViewModelActivity
import com.loongwind.ardf.base.ext.toast
import com.loongwind.ardf.demo.databinding.ActivityPermissionBinding
import com.loongwind.ardf.demo.databinding.TestPageBinding

//泛型类型是布局通过 DataBinding 自动生成的 ViewDataBinding 类型
class PermissionActivity : BaseBindingActivity<ActivityPermissionBinding>() {


    override fun initDataBinding(binding: ActivityPermissionBinding) {
        val permissions = arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
        binding.requestPermission.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA),
                showPermanentlyDeniedDialog = true,
                onDenied = {
                    toast("权限申请失败")
                }) {
                toast("权限申请成功")
            }


            val permissions = arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
            val onDenied = {
                toast("权限申请失败")
            }
            requestPermissions(
                permissions,
                showPermanentlyDeniedDialog = true,
                onDenied = onDenied) {
                toast("权限申请成功")
            }
        }
    }

}