package com.mertkaragul.beplanner.View.Page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import com.mertkaragul.beplanner.Service.PermissionService
import com.mertkaragul.beplanner.View.Elements.BePButton
import com.mertkaragul.beplanner.View.Elements.BePText

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    permissionService: PermissionService = PermissionService()
) {
    val context = LocalContext.current
    val permission = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
    AnimatedVisibility(visible = (permission.status.shouldShowRationale || !permission.status.isGranted) , enter = fadeIn(), exit = fadeOut() ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(30.dp)) {
            BePText(text = "Permission denied, please go app settings and access notification permission", color = MaterialTheme.colorScheme.onPrimary)
            BePButton(text = "Go app settings", onClick = { permissionService.routePermissionPage(context) })
        }
    }

    LaunchedEffect(key1 = permission){
        permission.launchPermissionRequest()
    }
}

@Preview(showBackground =  true)
@Composable
fun PreviewPermission() {
    BePlannerTheme {
        Permission()
    }
}