package com.mertkaragul.beplanner.View.Page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.LaunchedEffect
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

    var showFloatButtonInHomePage by remember { mutableStateOf(true) }
    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp

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
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = it.time
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = if (!it.status) {
                                MaterialTheme.colorScheme.tertiaryContainer
                            } else {
                                MaterialTheme.colorScheme.primaryContainer
                            }
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 5.dp,
                                    top = 5.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BePText(
                                text = it.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Column(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                                    )
                                    .background(
                                        if (!it.status) {
                                            MaterialTheme.colorScheme.onTertiaryContainer
                                        } else {
                                            MaterialTheme.colorScheme.onPrimaryContainer
                                        }
                                    )
                                    .padding(10.dp)
                            ) {
                                BePText(
                                    text = if (!it.status){"Not reminded"} else {"Reminded"},

                                    color = if (!it.status) {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else {
                                        MaterialTheme.colorScheme.onPrimary
                                    }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 5.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                BePText(
                                    text = "Date : ${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)}/${calendar.get(Calendar.YEAR)}",
                                    color = MaterialTheme.colorScheme.primary
                                )
                                BePText(
                                    text = "Hour : ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }


                            if (it.status){
                                BePIconButton(
                                    icon = R.drawable.baseline_delete_24,
                                    onClick = {
                                        if (data?.isNotEmpty() == true){
                                            viewmodel.deletePlan(it.uuid)
                                            viewmodel.getPlans()
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
            BePIconButton(icon = R.drawable.baseline_refresh_24,
                onClick = { viewmodel.getPlans() },
                modifier = Modifier
                    .height((height * .9).dp)
                    .width((width * .12).dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(5.dp)
            )
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