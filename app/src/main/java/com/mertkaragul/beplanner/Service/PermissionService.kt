package com.mertkaragul.beplanner.Service

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.security.Permission

class PermissionService {
    fun checkPermission(context: Context, permission : String) : Boolean {
        return ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
    }
}