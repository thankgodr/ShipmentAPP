package com.thangodr.shipmentappui.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class VehicleType(
    @StringRes val nameRes: Int,
    @StringRes val typeRes: Int,
    @DrawableRes val imageRes: Int
)
