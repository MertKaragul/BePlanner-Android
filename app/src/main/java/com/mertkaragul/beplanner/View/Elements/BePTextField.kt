package com.mertkaragul.beplanner.View.Elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BePTextField(
    value : String,
    onValueChange : (String) -> Unit,
    errorText : String = "Error, please fill text",
    placeholder : String
) {
    var isError by remember {
        mutableStateOf(false)
    }

    var showPlaceHolder by remember {
        mutableStateOf(true)
    }

    val deviceHeight= LocalConfiguration.current.screenHeightDp
    val deviceWidth = LocalConfiguration.current.screenWidthDp
    Column(
        modifier = Modifier
            .height((deviceHeight * 0.133).dp),
    ) {
        Box{
            TextField(
                value = value,
                onValueChange = {
                    isError = (it.isEmpty() && it.isBlank())
                    onValueChange(it)
                },
                isError = isError,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.primary,
                    containerColor = if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer,
                    errorCursorColor = MaterialTheme.colorScheme.error,
                    errorLabelColor = MaterialTheme.colorScheme.onError,
                    errorIndicatorColor = Color.Transparent,
                    errorLeadingIconColor = MaterialTheme.colorScheme.error,
                    errorSupportingTextColor = MaterialTheme.colorScheme.error,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.clickable {
                    showPlaceHolder = !showPlaceHolder
                },
                label = {
                    Text(
                        text = placeholder,
                        color = if (isError) MaterialTheme.colorScheme.error.copy(0.5F)
                        else MaterialTheme.colorScheme.primary
                    )
                }
            )
        }

        Box{
            this@Column.AnimatedVisibility(visible = isError, enter = fadeIn() , exit = fadeOut(),modifier = Modifier.padding(5.dp)) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error.copy(0.5F)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBepTextFields() {
    BePlannerTheme {
        var value by remember {
            mutableStateOf("")
        }

        BePTextField(value, onValueChange = {value = it} , "Lütfen yazıyı doldurun" , "İsim")
    }
}