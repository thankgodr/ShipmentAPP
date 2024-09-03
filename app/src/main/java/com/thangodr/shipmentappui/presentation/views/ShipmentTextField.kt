package com.thangodr.shipmentappui.presentation.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.ui.theme.FIELD_GREY

@Composable
fun ShipmentTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholderString: String,
    @DrawableRes leadingIconRes: Int? = null
){
      OutlinedTextField(
          value = text,
          onValueChange = onTextChange,
          shape = RoundedCornerShape( 16.dp),
          colors = OutlinedTextFieldDefaults.colors(
              unfocusedContainerColor = FIELD_GREY,
              focusedContainerColor = FIELD_GREY,
          ),
          leadingIcon = if(leadingIconRes == null) null else {
              {
                  Row(
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(8.dp)
                  ){
                      Image(
                          painter = painterResource(id = leadingIconRes),
                          contentDescription = null,
                          modifier = Modifier.size(16.dp),
                      )

                      VerticalDivider(
                          modifier = Modifier.height(16.dp)
                      )

                  }
              }
          },
          placeholder = {
              Text(
                  text = placeholderString,
                  color = Color(0xFF959494)
              )
          }
      )
}

@Preview
@Composable
fun prevEdit(){
    ShipmentTextField(
        text = "", onTextChange = {} , placeholderString = "Testdf",
        leadingIconRes = R.drawable.search_icon
    )
}