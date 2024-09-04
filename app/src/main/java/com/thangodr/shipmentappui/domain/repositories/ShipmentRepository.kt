package com.thangodr.shipmentappui.domain.repositories

import com.thangodr.shipmentappui.domain.models.Shipments

interface ShipmentRepository {
    suspend fun getShipments(): List<Shipments>
}