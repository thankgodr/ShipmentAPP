package com.thangodr.shipmentappui.presentation.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thangodr.shipmentappui.ui.theme.APP_ORANGE
import com.thangodr.shipmentappui.ui.theme.FIELD_GREY

@Composable
fun ShipmentButtton(
    modifier: Modifier = Modifier,
    canEnable: Boolean = true,
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.Black,
            contentColor = Color.White,
            backgroundColor = APP_ORANGE
        ),
        enabled = canEnable
    ) {
        Text(text = buttonText)
    }
}