package com.thangodr.shipmentappui.data.repository

import com.thangodr.shipmentappui.domain.models.ShipmentStatus
import com.thangodr.shipmentappui.domain.models.Shipments
import com.thangodr.shipmentappui.domain.repositories.ShipmentRepository
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class ShipmentRepositoryImp: ShipmentRepository {
    override suspend fun getShipments(): List<Shipments> {
       return  generateRandomShipments(10)
    }

    //Just for test sakes
   private fun generateRandomShipments(count: Int): List<Shipments> {
        val itemNames = listOf("Laptop", "Smartphone", "Books", "Clothes", "Furniture", "Toys", "Camera", "Shoes", "Bicycle", "Headphones")
        val senderNames = listOf("John Doe", "Jane Smith", "Michael Johnson", "Emily Davis", "David Brown", "Chris Evans", "Emma Wilson", "Olivia Harris", "William Robinson", "Sophia Clark")
        val locations = listOf("New York, NY", "Los Angeles, CA", "Chicago, IL", "Houston, TX", "Phoenix, AZ", "Philadelphia, PA", "San Antonio, TX", "San Diego, CA", "Dallas, TX", "San Jose, CA")
        val packageTypes = listOf("Box", "Envelope", "Crate", "Tube", "Pallet")
        val statuses = ShipmentStatus.values()

        return List(count) {
            Shipments(
                itemName = itemNames.random(),
                shipmentNumber = "#${UUID.randomUUID().toString()}",
                weight = Random.nextDouble(0.5, 30.0),
                senderName = senderNames.random(),
                senderLocation = locations.random(),
                receiversName = senderNames.random(),
                receiversLocation = locations.random(),
                packageType = packageTypes.random(),
                status = statuses.random(),
                deliveryDay = Date(System.currentTimeMillis() + Random.nextLong(1, 10) * 24 * 60 * 60 * 1000) // Random delivery date within the next 10 days
            )
        }
    }
}