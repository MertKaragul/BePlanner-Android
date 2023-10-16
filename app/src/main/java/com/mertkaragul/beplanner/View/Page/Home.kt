package com.mertkaragul.beplanner.View.Page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.Viewmodel.HomeViewModel
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun Home(
    viewmodel : HomeViewModel = viewModel()
) {
    val deviceHeight = LocalConfiguration.current.screenHeightDp
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val data = viewmodel.plansLiveData.observeAsState()

        if(data.value.isNullOrEmpty() && data.value.isNullOrEmpty()) {
            Column {
                BePText(text = "Vow so much emoty")
            }
        }else{
            LazyColumn{
                items(data.value?.size ?: 0){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height((deviceHeight * .12).dp)
                        .padding(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            BePText(text = data.value!![it].name , fontWeight = FontWeight.Bold, fontSize = 30.sp)
                            BePText(text = data.value!![it].status.toString())
                        }

                    }
                }
            }
        }

    }

    LaunchedEffect(key1 = Unit){
        viewmodel.getPlans()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    BePlannerTheme {
        Home()
    }
}