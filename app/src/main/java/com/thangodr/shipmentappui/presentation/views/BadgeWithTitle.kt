package com.thangodr.shipmentappui.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.thangodr.shipmentappui.ui.theme.APP_ORANGE
import com.thangodr.shipmentappui.ui.theme.APP_WHITE_BG

@Composable
fun BadgeWithTitle(
    title: String,
    badgeCount: Int?,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .then(if (isSelected) Modifier.drawBehind {
                val strokeWidth = 4.dp.toPx()
                drawLine(
                    color = APP_ORANGE,
                    start = this.center.copy(x = 0f, y = this.size.height),
                    end = this.center.copy(x = this.size.width, y = this.size.height),
                    strokeWidth = strokeWidth
                )
            } else Modifier)
            .clickable { onClick() }
    ) {
        BodyText(text = title, color = if(isSelected) Color.White else Color(0xFFbfafe7) )

        if (badgeCount != null) {
                Box(modifier = Modifier.size(20.dp).background(if(isSelected) APP_ORANGE else Color(0xFF7357c5), CircleShape)){
                    BodyText(
                        text = badgeCount.toString(),
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.body2.copy(
                            color = if(isSelected) Color.White else Color(0xFFbcafe8)
                        )
                    )
                }
        }
    }
}