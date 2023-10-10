package com.mertkaragul.beplanner.View.Elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BePTextField(
    value : String,
    onValueChange : (String) -> Unit,
    errorText : String = "Error, please fill text"
) {
    var isError by remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                isError = it.isEmpty()
                onValueChange(it)
            },
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer,
                errorCursorColor = MaterialTheme.colorScheme.error,
                errorLabelColor = MaterialTheme.colorScheme.onError,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
                errorLeadingIconColor = MaterialTheme.colorScheme.error,
                errorSupportingTextColor = MaterialTheme.colorScheme.error,
            ),
        )
        AnimatedVisibility(visible = isError, enter = fadeIn() , exit = fadeOut()) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBepTextFields() {
    BePlannerTheme {
       Column(Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           var value by remember {
               mutableStateOf("Text")
           }

           BePTextField(value, onValueChange = {value = it} , "Lütfen yazıyı doldurun")
       }
    }
}