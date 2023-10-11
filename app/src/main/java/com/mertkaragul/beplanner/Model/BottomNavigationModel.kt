package com.mertkaragul.beplanner.Model

import androidx.compose.runtime.Composable
import com.mertkaragul.beplanner.Enum.RouteEnum

data class BottomNavigationModel(
    val title : String,
    val icon : Int,
    val routeEnum: RouteEnum
)
