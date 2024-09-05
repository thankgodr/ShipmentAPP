package com.thangodr.shipmentappui.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.domain.models.Shipments
import com.thangodr.shipmentappui.domain.models.VehicleType
import com.thangodr.shipmentappui.presentation.views.BodyText
import com.thangodr.shipmentappui.presentation.views.HomeSearchDetailsView
import com.thangodr.shipmentappui.presentation.views.ShipmentSearchField
import com.thangodr.shipmentappui.ui.theme.APP_BLUE
import com.thangodr.shipmentappui.ui.theme.APP_ORANGE
import com.thangodr.shipmentappui.ui.theme.APP_WHITE_BG
import com.thangodr.shipmentappui.ui.theme.APP_WHite

@Composable
fun HomeScreens(
   modifier: Modifier = Modifier,
   searchStringProvider: () -> String,
   onSearchStringChange: (String) -> Unit,
   shipmentProvider: () -> List<Shipments>
){

    var searchHasFocus by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
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
              val (profilePic, locationArrow, yourLocationTitle, locationValue, locationDropDown, notification, backBtn,searchView, spacer) = createRefs()

              if(!searchHasFocus){
                  Image(
                      painter = painterResource(id = R.drawable.ic_launcher_background),
                      contentDescription = stringResource(id = R.string.user_picture),
                      modifier = Modifier
                          .size(60.dp)
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
                          .constrainAs(locationArrow) {
                              top.linkTo(parent.top, 24.dp)
                              start.linkTo(profilePic.end, 8.dp)
                          }
                  )

                  BodyText(
                      text = stringResource(id = R.string.your_location),
                      modifier = Modifier.constrainAs(yourLocationTitle){
                          centerVerticallyTo(locationArrow)
                          start.linkTo(locationArrow.end, 4.dp)
                      },
                      color = Color(0xFFaa99d5)
                  )

                  BodyText(
                      text = stringResource(id = R.string.location_demo),
                      modifier = Modifier.constrainAs(locationValue){
                          top.linkTo(locationArrow.bottom, 4.dp)
                          start.linkTo(profilePic.end, 8.dp)
                      },
                      color = APP_WHite
                  )

                  Icon(
                      imageVector = Icons.Default.KeyboardArrowDown,
                      contentDescription = stringResource(id = R.string.dropdown),
                      modifier = Modifier.constrainAs(locationDropDown){
                          centerVerticallyTo(locationValue)
                          start.linkTo(locationValue.end, 4.dp)
                      },
                      tint = APP_WHite
                  )

                  Card(
                      modifier = Modifier
                          .size(60.dp)
                          .constrainAs(notification) {
                              top.linkTo(parent.top, 20.dp)
                              end.linkTo(parent.end, 20.dp)
                          },
                      shape = CircleShape,
                      contentColor = APP_WHITE_BG
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
              }

              if(searchHasFocus){
                  Image(
                      painter = painterResource(id = R.drawable.back_arrow) ,
                      contentDescription = stringResource(id = R.string.back),
                      modifier = Modifier
                          .constrainAs(backBtn) {
                              centerVerticallyTo(searchView)
                              start.linkTo(parent.start, 20.dp)
                          }
                          .clickable {
                              keyboardController?.hide()
                              focusManager.clearFocus()
                          }
                  )
              }
              ShipmentSearchField(
                  onSearchTextChanged = onSearchStringChange ,
                  text =  searchStringProvider(),
                  placeHolderText = stringResource(id = R.string.enter_the_receipt_number),
                  modifier = Modifier
                      .constrainAs(searchView) {
                          top.linkTo(if (searchHasFocus) parent.top else profilePic.bottom, 20.dp)
                          start.linkTo(
                              if (searchHasFocus) backBtn.end else parent.start,
                              if (searchHasFocus) 8.dp else 20.dp
                          )
                          end.linkTo(parent.end, 20.dp)
                          width = Dimension.fillToConstraints
                      }
                      .onFocusChanged {
                          searchHasFocus = it.hasFocus
                          if (!it.hasFocus) {
                              onSearchStringChange("")
                          }
                      },
                  backgroundColor = APP_WHITE_BG
              )

              Spacer(modifier = Modifier.constrainAs(spacer) {
                  top.linkTo(searchView.bottom, 25.dp)
              })
          }
        val slideInAnimation = remember {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 600)
            ) + fadeIn(animationSpec = tween(durationMillis = 600))
        }

        val slideOutAnimation = remember {
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 600)
            ) + fadeOut(animationSpec = tween(durationMillis = 600))
        }
           if(searchHasFocus){
               ConstraintLayout(modifier = Modifier.constrainAs(content) {
                   top.linkTo(header.bottom, 20.dp)
                   start.linkTo(parent.start, 20.dp)
                   end.linkTo(parent.end, 20.dp)
                   width = Dimension.fillToConstraints
               }) {
                   AnimatedVisibility(
                       visible = searchHasFocus,
                       enter = slideInAnimation,
                       exit = slideOutAnimation
                   ) {
                       HomeSearchDetailsView(shipmentProvider())
                   }
               }
           }else{
               ConstraintLayout(modifier = Modifier.constrainAs(content) {
                   top.linkTo(header.bottom, 25.dp)
                   start.linkTo(parent.start, 20.dp)
                   end.linkTo(parent.end, 20.dp)
                   width = Dimension.fillToConstraints
               })
               {
                   val (trackingTitle, shipmentView, vehicleTile, vehicleList) = createRefs()

                   BodyText(
                       text = stringResource(id = R.string.tracking),
                       modifier = Modifier.constrainAs(trackingTitle){
                           top.linkTo(parent.top)
                           start.linkTo(parent.start)
                       },
                       style = MaterialTheme.typography.h6.copy(
                           color = Color(0xFF2d3650)
                       )
                   )

                   HomeShipmentCard(
                       modifier = Modifier.constrainAs(shipmentView){
                           top.linkTo(trackingTitle.bottom, 25.dp)
                           start.linkTo(parent.start)
                           end.linkTo(parent.end)
                           width = Dimension.fillToConstraints
                       }
                   )

                   BodyText(
                       text = stringResource(id = R.string.available_vehicles),
                       modifier = Modifier.constrainAs(vehicleTile){
                           top.linkTo(shipmentView.bottom, 25.dp)
                           start.linkTo(parent.start)
                       },
                       style = MaterialTheme.typography.h6.copy(
                           color = Color(0xFF2d3650)
                       )
                   )

                   LazyRow(
                       modifier = Modifier
                           .fillMaxWidth()
                           .constrainAs(vehicleList) {
                               top.linkTo(vehicleTile.bottom, 25.dp)
                           },
                       horizontalArrangement = Arrangement.spacedBy(16.dp)
                   ) {
                       items(listOf(
                           VehicleType(
                               R.string.oceen_freight,
                               R.string.international,
                               R.drawable.ship_svgrepo_com__1_
                           ),
                           VehicleType(
                               R.string.cargo_freight,
                               R.string.reliable,
                               R.drawable.cargo_truck_construction_svgrepo_com
                           ),
                           VehicleType(
                               R.string.air_freight,
                               R.string.international,
                               R.drawable.flight_plane_svgrepo_com
                           ),
                           VehicleType(
                               R.string.oceen_freight,
                               R.string.international,
                               R.drawable.ship_svgrepo_com__1_
                           ),
                           VehicleType(
                               R.string.cargo_freight,
                               R.string.reliable,
                               R.drawable.cargo_truck_construction_svgrepo_com
                           ),
                           VehicleType(
                               R.string.air_freight,
                               R.string.international,
                               R.drawable.flight_plane_svgrepo_com
                           )

                       )){
                           VehicleTypeView(vehicleType = it)
                       }
                   }

               }
           }

      }
}


@Composable
fun HomeShipmentCard(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (shipmentNumberTitle, shipmentNumberValue, cargoImageView,
                divider, senderImage, senderText, senderLocation, timeText) = createRefs()
            val (receiverImage, receiverTitle, receiverLocation, status, divider2, addBtn) = createRefs()

            BodyText(
                text = stringResource(id = R.string.shipment_number),
                modifier = Modifier.constrainAs(shipmentNumberTitle){
                    top.linkTo(parent.top, 25.dp)
                    start.linkTo(parent.start, 20.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFFBEBEBE)
                )
            )


            BodyText(
                text = "NEJ20000B999342222431",
                modifier = Modifier.constrainAs(shipmentNumberValue){
                    top.linkTo(shipmentNumberTitle.bottom, 4.dp)
                    start.linkTo(parent.start, 20.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFF20262f),
                    fontWeight = FontWeight.W600
                )
            )

            Image(
                painter = painterResource(id = R.drawable.noun_bendi_truck_4236946),
                contentDescription = stringResource(id = R.string.truck_image),
                colorFilter = ColorFilter.tint(APP_ORANGE),
                modifier = Modifier.constrainAs(cargoImageView){
                    top.linkTo(parent.top, 10.dp)
                    end.linkTo(parent.end, 20.dp)
                }
            )

            HorizontalDivider(
                modifier = Modifier.constrainAs(divider){
                    top.linkTo(shipmentNumberValue.bottom, 12.dp)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end,20.dp)
                    width = Dimension.fillToConstraints

                },
                color = Color.Gray,
                thickness = 0.8.dp
            )

            Card(
                modifier = Modifier
                    .size(40.dp)
                    .constrainAs(senderImage) {
                        top.linkTo(divider.bottom, 16.dp)
                        start.linkTo(parent.start, 20.dp)
                    },
                shape = CircleShape,
                backgroundColor = Color(0xFFfee6d5)
            ) {

            }

            BodyText(
                text = stringResource(id = R.string.sender),
                modifier = Modifier.constrainAs(senderText){
                    top.linkTo(senderImage.top, 8.dp)
                    start.linkTo(senderImage.end, 4.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFFBEBEBE),
                    lineHeight = 8.sp
                )
            )
            BodyText(
                text = "Atlanta, 5243",
                modifier = Modifier.constrainAs(senderLocation){
                    top.linkTo(senderText.bottom)
                    start.linkTo(senderImage.end, 6.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFF535458),
                    fontWeight = FontWeight.W500
                )
            )

            Column(modifier = Modifier.constrainAs(timeText){
                top.linkTo(senderText.top)
                start.linkTo(status.start)
            }) {
                BodyText(
                    text = stringResource(id = R.string.time),
                    style = MaterialTheme.typography.body2.copy(
                        color = Color(0xFFBEBEBE),
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Canvas(modifier = Modifier
                        .padding(start = 2.dp, end = 4.dp)
                        .size(4.dp)){
                        drawCircle(Color(0xFF49ca33))
                    }
                    BodyText(
                        text = "2 day - 3 days",
                        style = MaterialTheme.typography.body2.copy(
                            color = Color(0xFF535458),
                            fontWeight = FontWeight.W500
                        )
                    )
                }
            }


            //Receiver section

            Card(
                modifier = Modifier
                    .size(40.dp)
                    .constrainAs(receiverImage) {
                        top.linkTo(senderLocation.bottom, 25.dp)
                        start.linkTo(parent.start, 20.dp)
                    },
                shape = CircleShape,
                backgroundColor = Color(0xFFfee6d5)
            ) {

            }

            BodyText(
                text = stringResource(id = R.string.receiver),
                modifier = Modifier.constrainAs(receiverTitle){
                    top.linkTo(receiverImage.top, 8.dp)
                    start.linkTo(senderImage.end, 4.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFFBEBEBE),
                    lineHeight = 8.sp
                )
            )
            BodyText(
                text = "Chicago, 2345",
                modifier = Modifier.constrainAs(receiverLocation){
                    top.linkTo(receiverTitle.bottom)
                    start.linkTo(senderImage.end, 6.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFF535458),
                    fontWeight = FontWeight.W500
                )
            )

            Column(modifier = Modifier.constrainAs(status){
                top.linkTo(receiverTitle.top)
                end.linkTo(parent.end, 20.dp)
            }) {
                BodyText(
                    text = stringResource(id = R.string.status),
                    style = MaterialTheme.typography.body2.copy(
                        color = Color(0xFFBEBEBE),
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Start
                    )
                )
                BodyText(
                    text = stringResource(id = R.string.waiting_to_collect),
                    style = MaterialTheme.typography.body2.copy(
                        color = Color(0xFF535458),
                        fontWeight = FontWeight.W500
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier.constrainAs(divider2){
                    top.linkTo(status.bottom, 25.dp)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end,20.dp)
                    width = Dimension.fillToConstraints

                },
                color = Color.Gray,
                thickness = 0.8.dp
            )

            Button(
                modifier = Modifier.constrainAs(addBtn){
                    top.linkTo(divider2.bottom)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = APP_ORANGE,

                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                ),
                onClick = {

                }
            ) {
                   Text(text = "+ Add Stop")
            }


        }

    }
}

@Composable
fun VehicleTypeView(
    vehicleType: VehicleType,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
        ) {
            ConstraintLayout(modifier =Modifier.fillMaxWidth()) {
                val (name, type, image) = createRefs()

                BodyText(
                    text = stringResource(id = vehicleType.nameRes),
                    modifier = Modifier.constrainAs(name){
                        top.linkTo(parent.top, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    },
                    style = MaterialTheme.typography.body2.copy(
                        color = Color(0xFF40424b),
                        fontWeight = FontWeight.W500,
                        lineHeight = 12.sp
                    )
                )

                BodyText(
                    text = stringResource(id = vehicleType.typeRes),
                    modifier = Modifier.constrainAs(type){
                        top.linkTo(name.bottom)
                        start.linkTo(parent.start, 16.dp)
                    },
                    style = MaterialTheme.typography.body2.copy(
                        color = Color(0xFFBEBEBE),
                        fontSize = 10.sp
                    )
                )
                Image(
                    painter = painterResource(id = vehicleType.imageRes),
                    contentDescription = stringResource(id = vehicleType.nameRes),
                    modifier = Modifier
                        .width(150.dp)
                        .constrainAs(image) {
                            end.linkTo(parent.end)
                            top.linkTo(type.bottom, 25.dp)
                            start.linkTo(parent.start, 30.dp)
                            bottom.linkTo(parent.bottom, 20.dp)
                        }
                )
            }
        }
}
