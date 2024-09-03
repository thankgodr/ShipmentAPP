package com.thangodr.shipmentappui.domain

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

enum class ShipmentStatus {
    Pending, Completed, InProgress, Canceled
}

enum class ShipementCategory(val pricePerKG: Double){
    Electronic(10.0, ), Document(5.0), Glass(6.0),
    Food(90.0),
}