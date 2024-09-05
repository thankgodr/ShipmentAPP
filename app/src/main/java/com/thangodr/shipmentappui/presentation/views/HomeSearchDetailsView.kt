package com.thangodr.shipmentappui.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.domain.models.Shipments
import com.thangodr.shipmentappui.ui.theme.APP_BLUE

@Composable
fun HomeSearchDetailsView(
    shipmentList: List<Shipments>
){
   Card(
       modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp),
       shape = RoundedCornerShape(16.dp),
       backgroundColor  = Color.White
   ) {
       LazyColumn(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 10.dp, vertical = 20.dp),
           verticalArrangement = Arrangement.spacedBy(10.dp)
       ){
           itemsIndexed(shipmentList){ index, item ->
               ShiptmentItemView(item)
               if(index+1 != shipmentList.size){
                 HorizontalDivider(
                     modifier = Modifier.padding(top = 8.dp),
                     thickness = 0.8.dp
                 )
               }
           }
       }
   }
}

@Composable
fun ShiptmentItemView(shipments: Shipments){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier.size(24.dp),
            backgroundColor = APP_BLUE
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = shipments.itemName,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BodyText(
                text = shipments.itemName,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color(0xFF20262c),
                    fontWeight = FontWeight.W500
                )
            )
            BodyText(
                text = "${shipments.shipmentNumber.substring(0, 16)} • ${shipments.senderLocation} • ${shipments.receiversLocation}",
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFFababab),
                    fontWeight = FontWeight.W500
                )
            )
        }
    }
}