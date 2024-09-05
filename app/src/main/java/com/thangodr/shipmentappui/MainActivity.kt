package com.thangodr.shipmentappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.thangodr.shipmentappui.presentation.navigation.MainScreenView
import com.thangodr.shipmentappui.ui.theme.ShipmentAPPUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShipmentAPPUITheme {
                MainScreenView()
            }
        }
    }
}

