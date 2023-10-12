package com.mertkaragul.beplanner.View.Elements

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BePTextField(
    value : String,
    onValueChange : (String) -> Unit,
    errorText : String = "Error, please fill text",
    placeholder : String,
    enabled : Boolean = true,
    leadingIcon : () -> Unit = {}
) {
    var isError by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.wrapContentHeight(),
    ) {
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
                disabledIndicatorColor = Color.Transparent,

                disabledTextColor = MaterialTheme.colorScheme.primary.copy(0.4F)
            ),
            shape = RoundedCornerShape(10.dp),
            label = {
                AnimatedContent(
                    targetState = if (isError) errorText else placeholder,
                    label = ""
                ){
                    Text(
                        text = it,
                        color = if (isError) MaterialTheme.colorScheme.error.copy(0.5F) else MaterialTheme.colorScheme.primary,
                    )
                }
            },
            enabled = enabled,
            leadingIcon = {
                leadingIcon()
            }
        )
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