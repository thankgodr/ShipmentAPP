package com.thangodr.shipmentappui.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ShipmentSelectableTagGrid(
    tags: List<String>,
    selectedTag: String,
    onTagSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        modifier = modifier.padding(16.dp)
    ) {
        tags.forEach { tag ->
            val isSelected = tag == selectedTag
            val backgroundColor = if (isSelected) Color(0xFF162042) else Color.Transparent
            val textColor = if (isSelected) Color.White else Color.Black

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .border(1.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                    .clickable { onTagSelected(tag) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = tag,
                    color = textColor,
                    fontSize = 14.sp
                )
            }
        }
    }
}
