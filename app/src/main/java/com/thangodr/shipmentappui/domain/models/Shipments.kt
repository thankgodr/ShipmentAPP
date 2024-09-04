package com.thangodr.shipmentappui.domain.models

import androidx.annotation.StringRes
import com.thangodr.shipmentappui.R
import java.util.Date

data class Shipments(
    val itemName: String,
    val shipmentNumber: String,
    val weight: Double,
    val senderName: String,
    val senderLocation: String,
    val receiversName :String,
    val receiversLocation: String,
    val packageType: String,
    val status: ShipmentStatus,
    val deliveryDay: Date
)

enum class ShipmentStatus(@StringRes val nameRes: Int) {
    Pending(R.string.pending),
    Completed(R.string.completed),
    InProgress(R.string.in_progress),
    Canceled(R.string.canceled),
}