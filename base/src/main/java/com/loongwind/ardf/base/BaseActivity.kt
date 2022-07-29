package com.loongwind.ardf.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loongwind.ardf.base.permission.PermissionHelper
import pub.devrel.easypermissions.EasyPermissions

/**
 *Author: chengminghui
 *Time: 2019-09-03
 *Description: xxx
 */
open class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {


    fun requestPermissions(
        permissions: Array<out String>,
        showPermanentlyDeniedDialog: Boolean = false,
        onDenied: (() -> Unit)? = null,
        onGranted: () -> Unit
    ) {
        PermissionHelper.requestPermissions(this, permissions, showPermanentlyDeniedDialog, onDenied, onGranted)
    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    /**
     * 权限申请失败
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        PermissionHelper.onPermissionsDenied(this, requestCode, perms)
    }


    /**
     * 权限申请成功
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        PermissionHelper.onGranted(requestCode, perms)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        PermissionHelper.onActivityResult(this, requestCode, resultCode, data)
    }

    /**
     * 权限申请提示弹出框点击拒绝
     */
    override fun onRationaleDenied(requestCode: Int) {
        PermissionHelper.onRationaleDenied(requestCode)
    }

    /**
     * 权限申请提示框点击确定
     */
    override fun onRationaleAccepted(requestCode: Int) {

    }

}