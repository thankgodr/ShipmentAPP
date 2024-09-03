package com.thangodr.shipmentappui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.presentation.views.BodyText
import com.thangodr.shipmentappui.presentation.views.ShipmentSearchField
import com.thangodr.shipmentappui.ui.theme.APP_BLUE
import com.thangodr.shipmentappui.ui.theme.APP_WHITE_BG
import com.thangodr.shipmentappui.ui.theme.APP_WHite
import com.thangodr.shipmentappui.ui.theme.Placeholder_Grey

@Composable
fun HomeScreens(
   modifier: Modifier = Modifier
){
      ConstraintLayout(
          modifier
              .fillMaxSize()
              .background(APP_WHITE_BG)){
          val (header, content) = createRefs()

          ConstraintLayout(modifier = Modifier
              .constrainAs(header) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
                  width = Dimension.fillToConstraints
              }
              .background(APP_BLUE)
          ) {
              val (profilePic, locationArror, yourLocationTitle, locationvalue, locationDropDown, notification, searchView, spacer) = createRefs()

              Image(
                  painter = painterResource(id = R.drawable.ic_launcher_background),
                  contentDescription = stringResource(id = R.string.user_picture),
                  modifier = Modifier
                      .size(50.dp)
                      .clip(CircleShape)
                      .constrainAs(profilePic) {
                          top.linkTo(parent.top, 20.dp)
                          start.linkTo(parent.start, 20.dp)
                      }
              )

              Image(
                  painter = painterResource(id = R.drawable.fa_solid__location_arrow),
                  contentDescription = stringResource(id = R.string.search_icon),
                  modifier = Modifier
                      .size(16.dp)
                      .constrainAs(locationArror) {
                          top.linkTo(parent.top, 24.dp)
                          start.linkTo(profilePic.end, 8.dp)
                      }
              )

              BodyText(
                  text = stringResource(id = R.string.your_location),
                  modifier = Modifier.constrainAs(yourLocationTitle){
                       centerVerticallyTo(locationArror)
                      start.linkTo(locationArror.end, 4.dp)
                  },
                  color = Color(0xFFaa99d5)
              )

              BodyText(
                  text = stringResource(id = R.string.location_demo),
                  modifier = Modifier.constrainAs(locationvalue){
                      top.linkTo(locationArror.bottom, 4.dp)
                      start.linkTo(profilePic.end, 8.dp)
                  },
                  color = APP_WHite
              )

              Icon(
                  imageVector = Icons.Default.KeyboardArrowDown,
                  contentDescription = stringResource(id = R.string.dropdown),
                  modifier = Modifier.constrainAs(locationDropDown){
                      centerVerticallyTo(locationvalue)
                      start.linkTo(locationvalue.end, 4.dp)
                  },
                  tint = APP_WHite
              )

              Card(
                  modifier = Modifier
                      .size(50.dp)
                      .constrainAs(notification) {
                          top.linkTo(parent.top, 20.dp)
                          end.linkTo(parent.end, 20.dp)
                      },
                  shape = CircleShape,
                  colors = CardDefaults.cardColors(
                      contentColor = APP_WHITE_BG
                  )
              ) {
                  Box(modifier = Modifier.fillMaxSize()){
                      Image(
                          painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                          contentDescription = stringResource(id = R.string.notification),
                          contentScale = ContentScale.Crop,
                          modifier = Modifier.align(Alignment.Center)
                      )
                  }
              }

              ShipmentSearchField(
                  onSearchTextChanged = {} ,
                  text =  "",
                  placeHolderText = stringResource(id = R.string.enter_the_receipt_number),
                  modifier = Modifier.constrainAs(searchView){
                      top.linkTo(profilePic.bottom, 20.dp)
                      start.linkTo(parent.start, 20.dp)
                      end.linkTo(parent.end, 20.dp)
                      width = Dimension.fillToConstraints
                  },
                  backgroundColor = APP_WHITE_BG
              )

              Spacer(modifier = Modifier.constrainAs(spacer) {
                  top.linkTo(searchView.bottom, 20.dp)
              })
          }

          ConstraintLayout(modifier = Modifier.constrainAs(content) {
              top.linkTo(header.bottom, 20.dp)
              start.linkTo(parent.start, 20.dp)
              end.linkTo(parent.end, 20.dp)
              width = Dimension.fillToConstraints
          }) {
              val (trackingTitle, shipmentView) = createRefs()

              BodyText(
                  text = stringResource(id = R.string.tracking),
                  modifier = Modifier.constrainAs(trackingTitle){
                      top.linkTo(parent.top)
                      start.linkTo(parent.start)
                  },
                  style = MaterialTheme.typography.headlineSmall.copy(
                      color = Color(0xFF2d3650)
                  )
              )

              HomeShipmentCard(
                  modifier = Modifier.constrainAs(shipmentView){
                      top.linkTo(trackingTitle.bottom, 16.dp)
                      start.linkTo(parent.start)
                      end.linkTo(parent.end)
                      width = Dimension.fillToConstraints
                  }
              )
          }
      }
}


@Composable
fun HomeShipmentCard(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (shipmentNumberTitle, shipmentNumberValue, cargoImageView,
                divider, senderImage, senderText, senderLocation, timeText, timeValue) = createRefs()

            BodyText(
                text = stringResource(id = R.string.shipment_number),
                modifier = Modifier.constrainAs(shipmentNumberTitle){
                    top.linkTo(parent.top, 20.dp)
                    start.linkTo(parent.start, 20.dp)
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFBEBEBE)
                )
            )
        }

    }
}


@Preview
@Composable
fun PrevSS(){
    HomeScreens()
}

