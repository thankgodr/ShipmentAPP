package com.thangodr.shipmentappui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.presentation.viewmodels.CalculateScrenData
import com.thangodr.shipmentappui.presentation.views.BodyText
import com.thangodr.shipmentappui.presentation.views.Header
import com.thangodr.shipmentappui.presentation.views.ShipmentButtton
import com.thangodr.shipmentappui.presentation.views.ShipmentDropdown
import com.thangodr.shipmentappui.presentation.views.ShipmentSelectableTagGrid
import com.thangodr.shipmentappui.presentation.views.ShipmentTextField
import com.thangodr.shipmentappui.ui.theme.APP_WHITE_BG

@Composable
fun CalculateScreen(
    onBackClicked: () -> Unit,
    screenDataProvider: () -> CalculateScrenData,
    onUpdateScreenData: (CalculateScrenData) -> Unit,
){
    val screenData = screenDataProvider()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .clickable {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
        .background(APP_WHITE_BG)){
        val(header, destinationTitle, inputColumn, packageTitle, packageSubtitle, boxDropDown,
            categoryTitle, categorySubTiles, categoriesView, continueBtm) = createRefs()
        Header(
            title = stringResource(id = R.string.calculate),
            modifier = Modifier.constrainAs(header){
                top.linkTo(parent.top)
                start.linkTo(parent.start )
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            onBackClicked = onBackClicked
        )

        BodyText(
            text = stringResource(id = R.string.destination),
            modifier = Modifier.constrainAs(destinationTitle){
                top.linkTo(header.bottom, 20.dp)
                start.linkTo(parent.start, 20.dp)
            },
            style = MaterialTheme.typography.body1.copy(
                Color(0xFF162042),
                fontWeight = FontWeight.W700,
            )
        )

        Card(modifier = Modifier.constrainAs(inputColumn){
            top.linkTo(destinationTitle.bottom, 20.dp)
            start.linkTo(parent.start, 10.dp )
            end.linkTo(parent.end, 10.dp)
            width = Dimension.fillToConstraints},
            backgroundColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ShipmentTextField(
                    text = screenData.senderLocation,
                    onTextChange = {
                        onUpdateScreenData(screenData.copy(
                            senderLocation = it
                        ))
                    },
                    placeholderString =  stringResource(id = R.string.sender_location),
                    modifier = Modifier.fillMaxWidth(),
                    leadingIconRes = R.drawable.baseline_drive_folder_upload_24
                )
                ShipmentTextField(
                    text = screenData.receiverLocation,
                    onTextChange = {
                        onUpdateScreenData(screenData.copy(
                            receiverLocation = it
                        ))
                    },
                    placeholderString =  stringResource(id = R.string.receiver_location),
                    modifier = Modifier.fillMaxWidth(),
                    leadingIconRes = R.drawable.baseline_sim_card_download_24
                )
                ShipmentTextField(
                    text = screenData.weight,
                    onTextChange = {
                        onUpdateScreenData(screenData.copy(
                            weight = it
                        ))
                    },
                    placeholderString =  stringResource(id = R.string.aprox_weight),
                    modifier = Modifier.fillMaxWidth(),
                    leadingIconRes = R.drawable.baseline_hourglass_top_24
                )
            }
        }

        BodyText(
            text = stringResource(id = R.string.packaging),
            modifier = Modifier.constrainAs(packageTitle){
                top.linkTo(inputColumn.bottom, 30.dp)
                start.linkTo(parent.start, 20.dp)
            },
            style = MaterialTheme.typography.body1.copy(
                Color(0xFF162042),
                fontWeight = FontWeight.W700,
            )
        )

        BodyText(
            text = stringResource(id = R.string.what_are_you_sending),
            modifier = Modifier.constrainAs(packageSubtitle){
                top.linkTo(packageTitle.bottom, 8.dp)
                start.linkTo(parent.start, 20.dp)
            },
            style = MaterialTheme.typography.body1.copy(
                Color(0xFFBEBEBE),
                fontWeight = FontWeight.W500,
            )
        )

        ShipmentDropdown(
            selectedItem = "Box",
            items = listOf("Box", "Test"),
            onItemSelected = {},
            placeholderString = "Select one",
            modifier = Modifier.constrainAs(boxDropDown){
                top.linkTo(packageSubtitle.bottom, 20.dp)
                start.linkTo(parent.start, 20.dp )
                end.linkTo(parent.end, 20.dp)
                width = Dimension.fillToConstraints
            },
            leadingIconRes = R.drawable.box_icon_24
        )

        BodyText(
            text = stringResource(id = R.string.categories),
            modifier = Modifier.constrainAs(categoryTitle){
                top.linkTo(boxDropDown.bottom, 20.dp)
                start.linkTo(parent.start, 20.dp)
            },
            style = MaterialTheme.typography.body1.copy(
                Color(0xFF162042),
                fontWeight = FontWeight.W700,
            )
        )

        BodyText(
            text = stringResource(id = R.string.what_are_you_sending),
            modifier = Modifier.constrainAs(categorySubTiles){
                top.linkTo(categoryTitle.bottom, 8.dp)
                start.linkTo(parent.start, 20.dp)
            },
            style = MaterialTheme.typography.body1.copy(
                Color(0xFFBEBEBE),
                fontWeight = FontWeight.W500,
            )
        )

        ShipmentSelectableTagGrid(
            tags = screenData.categoryList,
            selectedTag = screenData.selectedCategory ,
            onTagSelected = {
                onUpdateScreenData(screenData.copy(
                    selectedCategory = it
                ))
            },
            modifier = Modifier.constrainAs(categoriesView){
                top.linkTo(categorySubTiles.bottom, 12.dp)
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                width = Dimension.fillToConstraints
            }
        )

        ShipmentButtton(
            buttonText = stringResource(id = R.string.continue_propmt),
            modifier = Modifier.constrainAs(continueBtm){
                bottom.linkTo(parent.bottom, 20.dp)
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                width = Dimension.fillToConstraints
            }
        ) {

        }




    }
}