package com.mertkaragul.beplanner.View.Page

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.mertkaragul.beplanner.View.Elements.BePButton
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.Viewmodel.PermissionViewModel
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    permissionViewModel: PermissionViewModel = viewModel()
) {
    Column {
        if (Build.VERSION.SDK_INT >= 31){
            val permissions = rememberMultiplePermissionsState(permissions = listOf(android.Manifest.permission.SCHEDULE_EXACT_ALARM, android.Manifest.permission.POST_NOTIFICATIONS))
            LaunchedEffect(key1 = Unit){
                permissions.launchMultiplePermissionRequest()
            }
        }else{
            val permissions = rememberMultiplePermissionsState(permissions = listOf(android.Manifest.permission.SET_ALARM, android.Manifest.permission.POST_NOTIFICATIONS))
            LaunchedEffect(key1 = Unit){
                permissions.launchMultiplePermissionRequest()
            }
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