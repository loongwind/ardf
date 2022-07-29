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
        private var permissionsRequestCode = 20212
    }

    val requestCode = permissionsRequestCode ++
}