package com.mertkaragul.beplanner.View.Page

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mertkaragul.beplanner.Viewmodel.PermissionViewModel
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    permissionViewModel: PermissionViewModel = viewModel()
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

    }
    val permissionList = permissionViewModel.checkPermissionModel.observeAsState()
    permissionViewModel.checkPermissions(context)

    if(!permissionList.value.isNullOrEmpty()){
        permissionList.value?.forEach {
            launcher.launch(arrayOf(it.permissionName))
        }
    }
}

@Preview(showBackground =  true)
@Composable
fun PreviewPermission() {
    BePlannerTheme {
        Permission()
    }
}