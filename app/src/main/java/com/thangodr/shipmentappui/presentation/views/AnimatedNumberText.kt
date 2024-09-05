package com.thangodr.shipmentappui.presentation.views

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun AnimatedNumberText(
    startValue: Int,
    endValue: Int,
    preText: String = "",
    postText: String = "",
    color: Color = Color(0xFF65c39b),
    durationMillis: Int = 2000,
    modifier: Modifier = Modifier
) {
    var currentValue by remember { mutableStateOf(startValue) }
    val animatedValue by animateIntAsState(
        targetValue = currentValue,
        animationSpec = tween(durationMillis = durationMillis), label = ""
    )

    LaunchedEffect(Unit) {
        delay(500) // Optional delay before starting the animation
        currentValue = endValue
    }

    BodyText(
        text = "$preText$animatedValue$postText",
        style = MaterialTheme.typography.h4,
        color = color,
        modifier = modifier
    )
}