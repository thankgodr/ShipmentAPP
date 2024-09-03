package com.thangodr.shipmentappui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thangodr.shipmentappui.presentation.screens.CalculateScreen
import com.thangodr.shipmentappui.presentation.screens.HomeScreens
import com.thangodr.shipmentappui.presentation.screens.ProfileScreen
import com.thangodr.shipmentappui.presentation.screens.ShipmentScreen
import com.thangodr.shipmentappui.ui.theme.APP_BLUE

object MainScreenPath {
    const val CALCULATE = "APP_CALCULATE"
    const val DASHBOARD = "APP_DASHBOARD"
    const val SHIPMENT = "APP_SHIPTMENT"
    const val PROFILE = "APP_PROFILE"
}

@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = APP_BLUE,
            darkIcons = false
        )
    }
    Scaffold(
        bottomBar = {
            DashboardNav(navController = navController)
        },
        content = {
            Box(modifier = Modifier.padding(it)){
                MainScreenContent(navController)
            }
        },
        modifier = Modifier.systemBarsPadding()
    )
}

@Composable
private fun MainScreenContent(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = MainScreenPath.DASHBOARD )
    {
        composable(MainScreenPath.DASHBOARD){
            HomeScreens()
        }

        composable(MainScreenPath.SHIPMENT){
            ShipmentScreen()
        }
        composable(MainScreenPath.CALCULATE){
            CalculateScreen()
        }
        composable(MainScreenPath.PROFILE){
            ProfileScreen()
        }
    }
}