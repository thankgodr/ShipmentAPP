package com.thangodr.shipmentappui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thangodr.shipmentappui.domain.models.ShipmentStatus
import com.thangodr.shipmentappui.presentation.screens.CalculateScreen
import com.thangodr.shipmentappui.presentation.screens.HomeScreens
import com.thangodr.shipmentappui.presentation.screens.ProfileScreen
import com.thangodr.shipmentappui.presentation.screens.ShipmentEstimateScreen
import com.thangodr.shipmentappui.presentation.screens.ShipmentScreen
import com.thangodr.shipmentappui.presentation.viewmodels.CalculateViewModel
import com.thangodr.shipmentappui.presentation.viewmodels.HomeViewModel
import com.thangodr.shipmentappui.ui.theme.APP_BLUE

object MainScreenPath {
    const val CALCULATE = "APP_CALCULATE"
    const val DASHBOARD = "APP_DASHBOARD"
    const val SHIPMENT = "APP_SHIPTMENT"
    const val PROFILE = "APP_PROFILE"
    const val ESTIMATE = "APP_ESTIMATE"
}

@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val bottomNavViewModel : BottomNavViewModel = viewModel()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = APP_BLUE,
            darkIcons = false
        )
    }
    Scaffold(
        bottomBar = {
           if(bottomNavViewModel.showNav){
               DashboardNav(navController = navController)
           }
        },
        content = {
            Box(modifier = Modifier.padding(it).padding(bottom = 16.dp)){
                MainScreenContent(navController, bottomNavViewModel)
            }
        },
        modifier = Modifier.systemBarsPadding()
    )
}

@Composable
private fun MainScreenContent(
    navController: NavHostController,
    bottomNavViewModel: BottomNavViewModel,
){
    val homeViewModel: HomeViewModel = viewModel()
    val calcualteViewModel: CalculateViewModel = viewModel()
    LaunchedEffect(Unit){
        homeViewModel.getShipments()
    }
    NavHost(
        navController = navController,
        startDestination = MainScreenPath.DASHBOARD )
    {
        composable(MainScreenPath.DASHBOARD){
            bottomNavViewModel.showNav = true
            HomeScreens(
                shipmentProvider = {
                    homeViewModel.screenData.filteredList
                },
                searchStringProvider = {
                    homeViewModel.screenData.shipmentSearchTerm
                },
                onSearchStringChange = {
                    homeViewModel.filterList(it)
                }
            )
        }

        composable(MainScreenPath.SHIPMENT){
            bottomNavViewModel.showNav = false
            ShipmentScreen(
                onBackClicked = {navController.popBackStack()},
                allShipmentProvider = {homeViewModel.screenData.shipmentList},
                pendingShipmentProvider = {homeViewModel.screenData.shipmentList.filter {
                    it.status == ShipmentStatus.Pending
                }},
                completedShipmentProvider = {homeViewModel.screenData.shipmentList.filter {
                    it.status == ShipmentStatus.Completed
                }},
                canceledShipmentProvider = {homeViewModel.screenData.shipmentList.filter {
                    it.status == ShipmentStatus.Canceled
                }},
                inProgressShipmentProvider ={ homeViewModel.screenData.shipmentList.filter {
                    it.status == ShipmentStatus.InProgress
                }},

            )
        }
        composable(MainScreenPath.CALCULATE){
            bottomNavViewModel.showNav = true
            CalculateScreen(
                onBackClicked = {navController.popBackStack()},
                onUpdateScreenData = {
                    calcualteViewModel.screenData = it
                },
                screenDataProvider = {
                    calcualteViewModel.screenData
                },
                onContinueClicked = {
                    navController.navigate(MainScreenPath.ESTIMATE)
                }
            )
        }
        composable(MainScreenPath.PROFILE){
            bottomNavViewModel.showNav = true
            ProfileScreen()
        }

        composable(MainScreenPath.ESTIMATE){
            bottomNavViewModel.showNav = false
            ShipmentEstimateScreen {
                navController.popBackStack(MainScreenPath.DASHBOARD, false)
            }

        }
    }
}