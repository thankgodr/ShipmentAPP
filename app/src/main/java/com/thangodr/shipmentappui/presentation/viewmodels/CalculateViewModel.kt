package com.thangodr.shipmentappui.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel @Inject constructor(): ViewModel() {
    var screenData by mutableStateOf(CalculateScreenData())
}

data class CalculateScreenData(
    val senderLocation : String = "",
    val receiverLocation: String = "",
    val weight: String = "",
    val selectedCategory: String = "",
    val categoryList : List<String> = listOf("Box", "Can", "Job", "Bank", "Rice", "laptop Charger", "Iphone 18")
)