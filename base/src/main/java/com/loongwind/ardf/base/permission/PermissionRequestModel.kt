package com.loongwind.ardf.base.permission

/**
 *Author: chengminghui
 *Time: 2020-02-12
 *Description: xxx
 */
data class PermissionRequestModel(
    val permissions: MutableList<String>,
    val showPermanentlyDeniedDialog : Boolean,
    val onDenied:()->Unit,
    val onGranted:()->Unit
) {

    companion object{
        // 权限申请的请求 Code
        private var permissionsRequestCode = 20212
    }

    // 请求 Code 每次在 permissionsRequestCode 上加 1
    val requestCode = permissionsRequestCode ++
}