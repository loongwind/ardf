package com.loongwind.ardf.base.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.loongwind.ardf.base.R
import com.loongwind.ardf.base.ext.toast
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 *Author: chengminghui
 *Time: 2020-02-13
 *Description: 权限申请辅助类
 */
object PermissionHelper {

    private val permissionsRequests = ArrayList<PermissionRequestModel>()

    private fun addPermissionRequest(permissionRequestModel: PermissionRequestModel){
        permissionsRequests.add(permissionRequestModel)
    }

    /**
     * 权限拒绝的处理
     */
    private fun onDenied(requestCode: Int, perms: List<String>) {
        val requestModel = getRequestModel(requestCode)
        requestModel?.let {
            it.permissions.removeAll(perms)
            println(perms)
            if(it.permissions.isEmpty()){
                it.onDenied.invoke()
                permissionsRequests.remove(it)
            }
        }

    }

    /**
     * 权限拒绝的处理
     */
    private fun onDenied(requestCode: Int) {
        val requestModel = getRequestModel(requestCode)
        requestModel?.let {
            it.onDenied.invoke()
            permissionsRequests.remove(it)
        }

    }

    /**
     * 权限申请失败
     */
    fun onPermissionsDenied(activity: Activity, requestCode: Int, perms: MutableList<String>) {

        getRequestModel(requestCode)?.let {
            //用户选择了不再提醒则引导用户去设置界面开启权限
            if (EasyPermissions.somePermissionPermanentlyDenied(activity, perms) && it.showPermanentlyDeniedDialog) {
                AppSettingsDialog.Builder(activity)
                    .setRequestCode(requestCode)
                    .setTitle(R.string.ardf_permission_setting_title)
                    .setRationale(R.string.ardf_permission_setting_describe)
                    .build().show()
            } else {
                onDenied(requestCode, perms)
            }
        }
    }

    /**
     * 权限申请失败
     */
    fun onPermissionsDenied(fragment: Fragment, requestCode: Int, perms: MutableList<String>) {
        getRequestModel(requestCode)?.let {
            //用户选择了不再提醒则引导用户去设置界面开启权限
            if (EasyPermissions.somePermissionPermanentlyDenied(fragment, perms) && it.showPermanentlyDeniedDialog) {
                AppSettingsDialog.Builder(fragment)
                    .setRequestCode(requestCode)
                    .setTitle(R.string.ardf_permission_setting_title)
                    .setRationale(R.string.ardf_permission_setting_describe)
                    .build().show()
            } else {
                onDenied(requestCode, perms)
            }
        }

    }

    /**
     * 权限申请成功
     */
    fun onGranted(requestCode: Int, perms: List<String>) {
        val requestModel = getRequestModel(requestCode)
        requestModel?.let {
            it.permissions.removeAll(perms)
            if(it.permissions.isEmpty()){
                it.onGranted.invoke()
                permissionsRequests.remove(it)
            }
        }
    }


    /**
     * 用户选择了拒绝不再提醒后引导去设置界面开启权限后返回界面的处理
     */
     fun onActivityResult(context: Context, requestCode: Int, resultCode: Int, data: Intent?) {

        //判断设置返回后权限是否开启
       getRequestModel(requestCode)?.let{
            if (EasyPermissions.hasPermissions(context, *it.permissions.toTypedArray())) {
                onGranted(requestCode, it.permissions)
            } else {
                onDenied(requestCode)
            }
        }
    }

    private fun getRequestModel(requestCode: Int) =
        permissionsRequests.find { it.requestCode == requestCode }


    fun requestPermissions(
        activity: Activity,
        permissions: Array<out String>,
        showPermanentlyDeniedDialog : Boolean,
        onDenied: (() -> Unit)?,
        onGranted: () -> Unit
    ) {
        //判断是否有权限
        if (EasyPermissions.hasPermissions(activity, *permissions)) {
            onGranted()
        } else {
            val requestModel = PermissionRequestModel(
                mutableListOf(*permissions),
                showPermanentlyDeniedDialog,
                onDenied ?: { defaultDeniedHandle(activity) },
                onGranted
            )
            addPermissionRequest(requestModel)
            EasyPermissions.requestPermissions(activity, activity.getString(R.string.ardf_permission_request_hint), requestModel.requestCode, *permissions)
        }
    }

    fun requestPermissions(
        fragment: Fragment,
        permissions: Array<out String>,
        showPermanentlyDeniedDialog : Boolean,
        onDenied: (() -> Unit)?,
        onGranted: () -> Unit
    ) {
        fragment.context?.let {
            if (EasyPermissions.hasPermissions(it, *permissions)) {
                onGranted()
            } else {
                val requestModel = PermissionRequestModel(
                    mutableListOf(*permissions),
                    showPermanentlyDeniedDialog,
                    onDenied ?: { defaultDeniedHandle(it) },
                    onGranted
                )
                addPermissionRequest(requestModel)
                EasyPermissions.requestPermissions(fragment, it.getString(R.string.ardf_permission_request_hint), requestModel.requestCode, *permissions)
            }
        }

    }


    /**
     * 默认的权限申请被拒绝的处理
     */
    private fun defaultDeniedHandle(context: Context?){
        context?.toast(R.string.ardf_permission_default_denied_hint)
    }


    /**
     * 权限申请提示弹出框点击拒绝
     */
    fun onRationaleDenied(requestCode: Int) {
        onDenied(requestCode)
    }




}