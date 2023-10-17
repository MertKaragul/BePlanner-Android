package com.mertkaragul.beplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.beplanner.Enum.RouteEnum
import com.mertkaragul.beplanner.Service.Database.DatabaseSetup
import com.mertkaragul.beplanner.Service.Database.DatabaseUtils
import com.mertkaragul.beplanner.View.Page.Home
import com.mertkaragul.beplanner.View.Page.RoutePage
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val databaseSetup = DatabaseSetup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BePlannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RoutePage()
                }
            }
        }
        databaseSetup.setupDatabase(applicationContext)
    }

    override fun onDestroy() {
        DatabaseUtils.database?.close()
        super.onDestroy()
    }
}
