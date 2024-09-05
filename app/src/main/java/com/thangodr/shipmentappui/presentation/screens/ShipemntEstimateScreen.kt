package com.thangodr.shipmentappui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.presentation.views.AnimatedNumberText
import com.thangodr.shipmentappui.presentation.views.BodyText
import com.thangodr.shipmentappui.presentation.views.ShipmentButtton

@Composable
fun ShipmentEstimateScreen(
    onHomeClicked: () -> Unit,
){
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (logo, boxImage, totalTitle, amount, amountSubtext, homeBtn) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.richard_logo),
            contentDescription = "ThankGod Richard Logo",
            modifier = Modifier.constrainAs(logo){
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.box_image),
            contentDescription = stringResource(id = R.string.truck_image),
            modifier = Modifier
                .size(100.dp)
                .constrainAs(boxImage) {
                    top.linkTo(logo.bottom)
                    centerHorizontallyTo(parent)
                }
        )

        BodyText(
            text = stringResource(id = R.string.total_estimate_amount),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.constrainAs(totalTitle){
                top.linkTo(boxImage.bottom, 20.dp)
                centerHorizontallyTo(parent)
            }
        )

        AnimatedNumberText(
            startValue = 1200,
            endValue = 1452,
            preText = "$",
            postText = " USD",
            modifier = Modifier.constrainAs(amount){
                top.linkTo(totalTitle.bottom, 20.dp)
                centerHorizontallyTo(parent)
            }
        )

        BodyText(
            text = stringResource(id = R.string.this_amount_is_estimated_long_text),
            style = MaterialTheme.typography.body2.copy(
                color = Color(0xFF949395)
            ),
            modifier = Modifier.constrainAs(amountSubtext){
                top.linkTo(amount.bottom, 20.dp)
                centerHorizontallyTo(parent)
            }
        )

        ShipmentButtton(
            buttonText = stringResource(id = R.string.back_to_home),
            modifier = Modifier.constrainAs(homeBtn){
                top.linkTo(amountSubtext.bottom, 40.dp)
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            onHomeClicked()
        }



    }
}