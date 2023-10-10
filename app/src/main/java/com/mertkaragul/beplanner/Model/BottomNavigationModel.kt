package com.mertkaragul.beplanner.Model

import com.mertkaragul.beplanner.Enum.RouteEnum

data class BottomNavigationModel(
    val title : String,
    val icon : Int,
    val routeEnum: RouteEnum
)
