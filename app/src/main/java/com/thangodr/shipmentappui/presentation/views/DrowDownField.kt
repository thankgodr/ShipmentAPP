package com.thangodr.shipmentappui.presentation.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.ui.theme.FIELD_GREY

///This need some more work  but not a requirement
@Composable
fun ShipmentDropdown(
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit,
    placeholderString: String,
    @DrawableRes leadingIconRes: Int? = null,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { /* Do nothing, handled by dropdown */ },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = if (leadingIconRes == null) null else {
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = leadingIconRes),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )

                        VerticalDivider(
                            modifier = Modifier.height(18.dp)
                        )
                    }
                }
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = "Dropdown",
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(if (expanded) 180f else 0f) // Rotate arrow when expanded
                )
            },
            placeholder = {
                Text(
                    text = placeholderString,
                    color = Color(0xFF959494)
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    content = { Text(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}