package com.mertkaragul.beplanner.Viewmodel

import android.content.Context
import android.os.Build
import android.os.Build.VERSION_CODES.S
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertkaragul.beplanner.Model.PermissionModel.PermissionModel
import com.mertkaragul.beplanner.Service.PermissionService

class PermissionViewModel : ViewModel() {
    private val permissionService = PermissionService()

    @RequiresApi(S)
    private val permissions = listOf(android.Manifest.permission.SCHEDULE_EXACT_ALARM)
    private val olderPermissions = listOf(android.Manifest.permission.POST_NOTIFICATIONS)

    private val _checkPermissionList = MutableLiveData<MutableList<PermissionModel>>(mutableListOf())
    val checkPermissionModel : LiveData<MutableList<PermissionModel>> = _checkPermissionList
    
    fun checkPermissions(context : Context){

        if (Build.VERSION.SDK_INT >= 31){
            permissions.forEach {
                _checkPermissionList.value?.add(PermissionModel(it,permissionService.checkPermission(context, it)))
            }
        }else{

        }

    }
}