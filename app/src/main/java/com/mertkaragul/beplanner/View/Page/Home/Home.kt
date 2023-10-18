package com.mertkaragul.beplanner.View.Page.Home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.View.Elements.BePButton
import com.mertkaragul.beplanner.View.Elements.BePDialog
import com.mertkaragul.beplanner.View.Elements.BePIconButton
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.Viewmodel.HomeViewModel
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme
import java.util.Calendar

@Composable
fun Home(
    viewmodel: HomeViewModel = viewModel(),
) {
    val data by viewmodel.plansLiveData.observeAsState()
    if(data?.isEmpty() == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BePText(text = "Wow, looks like no plan.\n Let's create a plan", fontSize = 25.sp, textAlign = TextAlign.Center)
        }
    }else{
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(data ?: mutableListOf()){
                    HomeCardList(it,viewmodel)
                }
            }

            /** Home view float action **/
            HomeFloatAction(viewmodel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    BePlannerTheme {
        Home()
    }
}