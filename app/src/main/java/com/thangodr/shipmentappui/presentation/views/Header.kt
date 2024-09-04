package com.thangodr.shipmentappui.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.ui.theme.APP_BLUE
import com.thangodr.shipmentappui.ui.theme.APP_WHite

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    onBackClicked: () -> Unit
){
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(APP_BLUE)
    ) {
        val(backBtn, titleView, spacer) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = stringResource(id = R.string.back),
            modifier = Modifier
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 20.dp)
                }
                .clickable {
                    onBackClicked()
                }
        )

        BodyText(
            text = title,
            modifier = Modifier.constrainAs(titleView){
                centerVerticallyTo(backBtn)
                centerHorizontallyTo(parent)
            },
            color = APP_WHite
        )

        Spacer(modifier = Modifier.constrainAs(spacer) {
            top.linkTo(titleView.bottom, 20.dp)
        })
    }
}