package com.zero.yoga.base

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.Logger

import java.util.*

/**
 * Created by zero on 2018/8/7.
 */
abstract class BasePermissionsActivity : BaseActivity() {

    companion object {
        var REQUEST_CODE_PERMISSION = 0x00099
        val TAG = "PermAct"
    }


    @Synchronized
    fun getManifestPermissions(activity: AppCompatActivity): Array<String> {
        var packageInfo: PackageInfo? = null
        val list = ArrayList<String>(1)
        try {
            Logger.t(TAG).d(activity.packageName)
            packageInfo = activity.packageManager.getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS)
        } catch (e: PackageManager.NameNotFoundException) {
            error("A problem occurred when retrieving permissions ${e.localizedMessage}")
        }

        if (packageInfo != null) {
            val permissions = packageInfo.requestedPermissions
            if (permissions != null) {
                for (perm in permissions) {
                    Logger.t(TAG).d("Manifest contained permission:$perm ")
                    list.add(perm)
                }
            }
        }
        return list.toTypedArray()
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    fun requestPermission(permissions: Array<String>, requestCode: Int) {
        REQUEST_CODE_PERMISSION = requestCode
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION)
        } else {
            val needPermissions = getDeniedPermissions(permissions)
            ActivityCompat.requestPermissions(this, needPermissions.toTypedArray(), REQUEST_CODE_PERMISSION)
        }
    }

    fun requestPermission(activity: AppCompatActivity) {
        if (activity == null) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        requestPermission(getManifestPermissions(activity), REQUEST_CODE_PERMISSION)
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    fun checkPermissions(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun checkPermissions(activity: AppCompatActivity): Boolean {
        if (activity == null) {
            return false
        }
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            true
        } else checkPermissions(getManifestPermissions(activity))
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private fun getDeniedPermissions(permissions: Array<String>): List<String> {
        val needRequestPermissionList = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission)
            }
        }
        return needRequestPermissionList
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION)
            } else {
                permissionFail(REQUEST_CODE_PERMISSION)
                showTipsDialog()
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 显示提示对话框
     */
    private fun showTipsDialog() {
        AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消") { dialog, which -> }
                .setPositiveButton("确定") { dialog, which -> startAppSettings() }.show()
    }

    /**
     * 启动当前应用设置页面
     */
    private fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + packageName)
        startActivity(intent)
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    open fun permissionSuccess(requestCode: Int) {

    }

    /**
     * 权限获取失败
     *
     * @param requestCode
     */
    fun permissionFail(requestCode: Int) {}

}