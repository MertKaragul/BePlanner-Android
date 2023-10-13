package com.mertkaragul.beplanner.Viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertkaragul.beplanner.Model.PermissionModel.PermissionModel
import com.mertkaragul.beplanner.Service.PermissionService
import kotlin.coroutines.coroutineContext

class PermissionViewModel : ViewModel() {
    private val permissionService = PermissionService()
    private val permissions = listOf(android.Manifest.permission.SCHEDULE_EXACT_ALARM)
    private val _checkPermissionList = MutableLiveData<MutableList<PermissionModel>>(mutableListOf())
    val checkPermissionModel : LiveData<MutableList<PermissionModel>> = _checkPermissionList
    fun checkPermissions(context : Context){
        permissions.forEach {
            _checkPermissionList.value?.add(PermissionModel(it,permissionService.checkPermission(context, it)))
        }
    }
}