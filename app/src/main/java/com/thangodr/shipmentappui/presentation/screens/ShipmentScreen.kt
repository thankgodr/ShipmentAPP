package com.thangodr.shipmentappui.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.domain.models.ShipmentStatus
import com.thangodr.shipmentappui.domain.models.Shipments
import com.thangodr.shipmentappui.presentation.views.BadgeWithTitle
import com.thangodr.shipmentappui.presentation.views.BodyText
import com.thangodr.shipmentappui.presentation.views.Header
import com.thangodr.shipmentappui.ui.theme.APP_BLUE
import com.thangodr.shipmentappui.ui.theme.APP_WHITE_BG
import kotlinx.coroutines.launch

@Composable
fun ShipmentScreen(
    onBackClicked: () -> Unit,
    allShipmentProvider: () -> List<Shipments>,
    pendingShipmentProvider: () -> List<Shipments>,
    completedShipmentProvider: () -> List<Shipments>,
    inProgressShipmentProvider: () -> List<Shipments>,
    canceledShipmentProvider: () -> List<Shipments>
){
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .clickable {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
        .background(APP_WHITE_BG)) {


        val (header, content) = createRefs()

        Header(
            title = stringResource(id = R.string.shipmet_history),
            modifier = Modifier.constrainAs(header){
                top.linkTo(parent.top)
                start.linkTo(parent.start )
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            onBackClicked = onBackClicked
        )
        val shipmentsArrays = ShipmentStatus.entries

        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { shipmentsArrays.size + 1 }
        )

        Column(modifier = Modifier.constrainAs(content){
            top.linkTo(header.bottom)
        }) {
            LazyRow(
                Modifier
                    .background(APP_BLUE)
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item{
                    BadgeWithTitle(
                        title = stringResource(id = R.string.all),
                        badgeCount =  allShipmentProvider().size, // Example condition for showing a badge
                        isSelected = pagerState.currentPage == 0,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    )
                }
                itemsIndexed(shipmentsArrays){ index, status ->
                    BadgeWithTitle(
                        title = stringResource(id = status.nameRes),
                        badgeCount =  when(status){
                            ShipmentStatus.Pending -> pendingShipmentProvider().size
                            ShipmentStatus.Canceled -> canceledShipmentProvider().size
                            ShipmentStatus.Completed -> completedShipmentProvider().size
                            ShipmentStatus.InProgress -> inProgressShipmentProvider().size
                        },
                        isSelected = pagerState.currentPage == index+1,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index+1)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .background(APP_WHITE_BG)
                    .weight(1f)
                    .fillMaxSize()
            ) { page ->
                if(page == 0){
                       PagerItem(list = allShipmentProvider()) 
                }else{
                    val item = shipmentsArrays[page-1]
                    when(item){
                        ShipmentStatus.Pending -> PagerItem(list = pendingShipmentProvider())
                        ShipmentStatus.Completed -> PagerItem(list = completedShipmentProvider())
                        ShipmentStatus.InProgress -> PagerItem(list = inProgressShipmentProvider())
                        ShipmentStatus.Canceled -> PagerItem(list = canceledShipmentProvider())
                    } 
                }
                
            }
        }
    }
}


@Composable
fun PagerItem(list: List<Shipments>) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(bottom = 20.dp)
    ){
        items(list){
            SingleShipmentView(shipments = it)
        }
    }
}

@Composable
fun SingleShipmentView(
    shipments: Shipments
){
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()) {
            val (status, arriving, delivery, amount, date, image, spacer) = createRefs()
            Card(
                modifier = Modifier.constrainAs(status){
                    top.linkTo(parent.top, 12.dp)
                    start.linkTo(parent.start, 12.dp)
                },
                backgroundColor = Color(0xf6f6f6),
                shape = RoundedCornerShape(100.dp)

            ) {
                Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                    Image(
                        painter = painterResource(id = shipments.getStatusImage()) ,
                        contentDescription = shipments.itemName,
                        colorFilter = ColorFilter.tint(shipments.getColor())
                    )

                    BodyText(
                        text = stringResource(id = shipments.status.nameRes),
                        color = shipments.getColor()
                    )
                }
            }

            BodyText(
                text = stringResource(id = R.string.arriving_today),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.constrainAs(arriving){
                    top.linkTo(status.bottom, 12.dp)
                    start.linkTo(parent.start, 12.dp)
                }
            )
            BodyText(
                text =stringResource(id = R.string.your_delivery, shipments.shipmentNumber.substring(0, 16), shipments.senderLocation),
                modifier = Modifier.constrainAs(delivery){
                    top.linkTo(arriving.bottom, 8.dp)
                    start.linkTo(parent.start, 12.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFFafaeb0)
                )
            )

            BodyText(
                text ="$1400 USD",
                modifier = Modifier.constrainAs(amount){
                    top.linkTo(delivery.bottom, 8.dp)
                    start.linkTo(parent.start, 12.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFF5a4b98),
                    fontWeight = FontWeight.W600
                )
            )

            BodyText(
                text =" • Sep 20, 2023",
                modifier = Modifier.constrainAs(date){
                    top.linkTo(delivery.bottom, 8.dp)
                    start.linkTo(amount.end, 12.dp)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = Color(0xFF797b85),
                    fontWeight = FontWeight.W600
                )
            )

            Image(painter = painterResource(id = R.drawable.box_image),
                contentDescription = shipments.itemName,
                modifier = Modifier
                    .size(70.dp)
                    .constrainAs(image) {
                        end.linkTo(parent.end, 30.dp)
                        centerVerticallyTo(parent)
                    }
            )
            Spacer(modifier = Modifier.constrainAs(spacer) {
                top.linkTo(date.bottom, 20.dp)
            })
        }
    }
}

fun Shipments.getColor(): Color{
    return when(this.status){
        ShipmentStatus.Pending -> Color(0xFFe89d5d)
        ShipmentStatus.Completed -> Color(0xFF4caf50)
        ShipmentStatus.InProgress -> Color(0xFFff9800)
        ShipmentStatus.Canceled -> Color(0xFFf44336)
    }
}
@DrawableRes
fun  Shipments.getStatusImage():  Int{
   return when(this.status){
        ShipmentStatus.Pending -> R.drawable.baseline_restart_alt_24
        ShipmentStatus.Completed -> R.drawable.baseline_check_24
        ShipmentStatus.InProgress -> R.drawable.baseline_lock_reset_24
        ShipmentStatus.Canceled -> R.drawable.baseline_not_interested_24
    }
}