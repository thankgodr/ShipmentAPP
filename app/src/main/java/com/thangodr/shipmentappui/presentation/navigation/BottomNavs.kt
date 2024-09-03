package com.thangodr.shipmentappui.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.thangodr.shipmentappui.R
import com.thangodr.shipmentappui.presentation.views.BodyText
import com.thangodr.shipmentappui.ui.theme.APP_WHITE_BG

val bottomNavList = listOf(
    BottomNavItem(
        R.string.home,
        R.drawable.home_icon_unselected,
        R.drawable.home_icon_selected,
        MainScreenPath.DASHBOARD
    ),
    BottomNavItem(
        R.string.calculate,
        R.drawable.calculate_icon_unselect,
        R.drawable.baseline_calculate_24,
        MainScreenPath.CALCULATE
    ),
    BottomNavItem(
        R.string.shipment,
        R.drawable.shipment_unselected,
        R.drawable.shipment_selected,
        MainScreenPath.SHIPMENT
    ),
    BottomNavItem(
        R.string.profile,
        R.drawable.person_unselected,
        R.drawable.person_selected,
        MainScreenPath.PROFILE
    )
)

@Composable
fun DashboardNav(
    navController: NavController,
    modifier: Modifier = Modifier,
){
    BottomNavigation(
        backgroundColor = APP_WHITE_BG,
        modifier = Modifier.fillMaxWidth()
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: MainScreenPath.DASHBOARD

        bottomNavList.forEach {  item ->
            val isSelected =  item.route == currentRoute
            val iconRes = if (isSelected) item.selectedIconRes else item.unselectedIconRes
            val textColor = if (isSelected) Color(0xFF604E9A) else Color(0xFFA9A8AF)
            BottomNavigationItem(
                selected = isSelected,
                onClick =
                {
                    navController.navigate(item.route)
                },
                icon = {
                    Box{
                        Divider(
                            color = if(isSelected) textColor else Color.Transparent,
                            thickness = 2.dp,
                            modifier = Modifier.align(Alignment.TopCenter).padding(bottom = 3.dp)
                        )
                        Icon(
                            painterResource(id = iconRes),
                            contentDescription = stringResource(id = item.titleRes),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                },
                label = {
                    BodyText(
                        text = stringResource(id = item.titleRes),
                        maxLines = 1,
                        style = MaterialTheme.typography.caption,
                        color = textColor
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = Color.Unspecified,
                unselectedContentColor = Color.Unspecified,
            )
        }

    }
}

data class BottomNavItem(
    @StringRes val titleRes: Int,
    @DrawableRes val unselectedIconRes: Int,
    @DrawableRes val selectedIconRes: Int,
    val route: String
)