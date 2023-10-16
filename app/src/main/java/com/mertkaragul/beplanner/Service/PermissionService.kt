package com.mertkaragul.beplanner.Service

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.security.Permission

class PermissionService {
    fun checkPermission(context: Context, permission : String) : Boolean {
        return ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
    }

    fun routePermissionPage(context : Context){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package",context.packageName,null)
        intent.data = uri
        context.startActivity(intent)
    }
}