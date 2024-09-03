package com.thangodr.shipmentappui.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.ui.theme.Placeholder_Grey
import com.thangodr.shipmentappui.ui.theme.Yellow

@Composable
fun ShipmentSearchField(
    onSearchTextChanged: (String) -> Unit,
    text: String,
    placeHolderText: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent
){
    OutlinedTextField(
        value = text,
        onValueChange = onSearchTextChanged,
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = backgroundColor,
            focusedContainerColor = backgroundColor
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search_icon) ,
                contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.FillBounds
            )
        },
        trailingIcon = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        shape = RoundedCornerShape(100.dp),
                        color = Yellow
                    ),

            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_newspaper_24),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.Center)
                )
            }
        },
        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Placeholder_Grey
                )
            )
        }
    )
}

@Preview
@Composable
fun TextPrev(){
    ShipmentSearchField(onSearchTextChanged = {}, text = "", placeHolderText = "Enter the receipt number")
}