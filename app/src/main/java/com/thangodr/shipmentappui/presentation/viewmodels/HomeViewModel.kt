package com.thangodr.shipmentappui.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thangodr.shipmentappui.commons.ResourceStatus
import com.thangodr.shipmentappui.domain.models.Shipments
import com.thangodr.shipmentappui.domain.repositories.ShipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shipmentRepository: ShipmentRepository
): ViewModel() {

    var screenData by mutableStateOf(HomeScreenData())

    private var _shipments = mutableStateOf<ResourceStatus<List<Shipments>>>(ResourceStatus.Idle())


    fun getShipments() = viewModelScope.launch {
        _shipments.value = ResourceStatus.Loading()
        screenData = screenData.copy(
            isLoading = true
        )
        val list = shipmentRepository.getShipments()
        screenData = screenData.copy(
            isLoading = false,
            shipmentList = list,
            filteredList =  if(screenData.filteredList.isEmpty()) list else screenData.filteredList
        )
        _shipments.value = ResourceStatus.Success(list) //simulating network calls
    }

    fun filterList(searchTerm: String) {
        screenData = screenData.copy(
            shipmentSearchTerm = searchTerm,
            filteredList = screenData.shipmentList.filter {
                it.itemName.contains(searchTerm)|| it.shipmentNumber.contains(searchTerm)
            }
        )
    }

}

data class HomeScreenData(
    val shipmentSearchTerm: String = "",
    val isLoading : Boolean = false,
    val shipmentList : List<Shipments> = emptyList(),
    val filteredList: List<Shipments> = emptyList()
)