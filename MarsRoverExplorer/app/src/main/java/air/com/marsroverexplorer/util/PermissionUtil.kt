package air.com.marsroverexplorer.util

import air.com.marsroverexplorer.util.AppMessage.OnPermissionExplanation
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionUtil {
    private val appMessage: AppMessage = AppMessage()

    companion object {
        const val REQUEST_PERMISSION_WRITE_DATA = 8374
    }

    fun requestWriteDataPermission(activity: Activity, message: String) : Boolean {
        if (checkPermission(activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            return true
        }

        appMessage.showPermissionExplanation(activity, message, object : OnPermissionExplanation {
            override fun onClickOk() {
                ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                                  REQUEST_PERMISSION_WRITE_DATA)
            }
        })

        return false
    }

     fun checkPermission(context: Context, permissions: Array<out String>): Boolean {
        for (permission: String in permissions) {
            val result = ContextCompat.checkSelfPermission(context, permission)
            if (result != PackageManager.PERMISSION_GRANTED) return false
        }

        return true
    }
}