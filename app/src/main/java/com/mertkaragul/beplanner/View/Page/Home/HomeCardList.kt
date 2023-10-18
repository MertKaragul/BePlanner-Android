package com.mertkaragul.beplanner.View.Page.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.View.Elements.BePIconButton
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.Viewmodel.HomeViewModel
import java.util.Calendar

@Composable
fun HomeCardList(it : DatabasePlanModel, viewModel: HomeViewModel) {
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
                text = it.name.let {
                    var totalText = ""
                    if (it.length >= 15){
                        it.forEachIndexed { index, c -> if (index <= 15) totalText += c }
                        totalText += "..."
                    }else{
                        totalText = it
                    }
                    totalText
                },
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
                    text = "Date : ${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)}/${calendar.get(
                        Calendar.YEAR)}",
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
                        viewModel.deletePlan(it.uuid)
                        viewModel.getPlans()
                    }
                )
            }
        }
    }
}