package com.mertkaragul.beplanner.View.Navigations

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.beplanner.Enum.RouteEnum
import com.mertkaragul.beplanner.Model.BottomNavigationModel
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun NavigationBottom(
    buttons: List<BottomNavigationModel>,
    navHostController: NavHostController
) {
    var clickedStatus by remember {
        mutableStateOf(buttons.first().routeEnum)
    }
    return NavigationBar {
        buttons.forEach {
            NavigationBarItem(
                selected = clickedStatus == it.routeEnum ,
                onClick = {
                    navHostController.navigate(it.routeEnum.toString())
                    clickedStatus = it.routeEnum
                },
                icon = {
                    Icon(painter = painterResource(id = it.icon), contentDescription = "")
                },
                label = {
                    Text(text = it.title)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewNavigationBottom() {
    BePlannerTheme {
        val bottomList = listOf(
            BottomNavigationModel("Home", icon = R.drawable.baseline_home_24 , RouteEnum.HOME_PAGE),
            BottomNavigationModel("Planner", icon = R.drawable.baseline_edit_calendar_24, RouteEnum.PLANNER_PAGE)
        )

        NavigationBottom(bottomList, rememberNavController())
    }
}