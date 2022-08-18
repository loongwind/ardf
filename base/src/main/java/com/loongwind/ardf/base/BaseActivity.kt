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


    /**
     * 权限申请
     * @param permissions 要申请的权限列表
     * @param showPermanentlyDeniedDialog 是否引导去应用设置赋予权限
     * @param onDenied 授权拒绝回调
     * @param onGranted 授权成功回调
     */
    fun requestPermissions(
        permissions: Array<out String>,
        showPermanentlyDeniedDialog: Boolean = false,
        onDenied: (() -> Unit)? = null,
        onGranted: () -> Unit
    ) {
        PermissionHelper.requestPermissions(this, permissions, showPermanentlyDeniedDialog, onDenied, onGranted)
    }


    /**
     * 权限申请结果回调
     */
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

    /**
     * 跳转设置界面授权后返回结果处理
     */
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