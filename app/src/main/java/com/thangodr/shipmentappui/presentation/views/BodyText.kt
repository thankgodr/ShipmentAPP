package com.thangodr.shipmentappui.presentation.views

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun BodyText(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = MaterialTheme.typography.body1,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    text: String
) {
    Text(
        text = text,
        style = style,
        color = color,
        fontSize = style.fontSize,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        lineHeight = if (lineHeight == TextUnit.Unspecified) lineHeight else lineHeight,
        textAlign = textAlign,
        modifier = modifier,
        textDecoration = textDecoration
    )
}