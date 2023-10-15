package com.mertkaragul.beplanner.View.Page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.beplanner.Enum.RouteEnum
import com.mertkaragul.beplanner.Model.BottomNavigationModel
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.View.Navigations.NavigationBottom
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutePage() {
    val rememberNavController = rememberNavController()
    val bottomList = listOf(
        BottomNavigationModel("Home", icon = R.drawable.baseline_home_24 , RouteEnum.HOME_PAGE ),
        BottomNavigationModel("Planner", icon = R.drawable.baseline_edit_calendar_24, RouteEnum.PLANNER_PAGE )
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBottom(model = bottomList, navHostController = rememberNavController)
        },
        topBar = {

        },
        content = {
            it.calculateTopPadding()
            NavHost(navController = rememberNavController, startDestination = RouteEnum.HOME_PAGE.toString() ){
                composable(RouteEnum.HOME_PAGE.toString()){
                    Home()
                }

                composable(RouteEnum.PLANNER_PAGE.toString()){
                    Planner()
                }
            }
            Permission()
        }
    )
}

@Preview
@Composable
fun PreviewRoutePage() {
    BePlannerTheme {
        RoutePage()
    }
}