package com.thangodr.shipmentappui.di

import com.thangodr.shipmentappui.data.repository.ShipmentRepositoryImp
import com.thangodr.shipmentappui.domain.repositories.ShipmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideShipmentRepository(): ShipmentRepository = ShipmentRepositoryImp()
}