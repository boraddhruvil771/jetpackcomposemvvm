package com.example.jetpackcomposebase.ui.custom_compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposebase.navigation.BottomNavItem

/**
 * This is Common Bottom Navigation Bar
 *
 */
@Composable
fun AppBottomNavigation(navController: NavController, bottomBarState: MutableState<Boolean>) {

    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Setting,
        BottomNavItem.Profile,
    )
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.onBackground,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                navItems.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = stringResource(item.title)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(item.title),
                                fontSize = 9.sp
                            )
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = currentRoute == item.navRoute.fullRoute,
                        onClick = {
                            navController.navigate(item.navRoute.fullRoute) {
                                navController.graph.startDestinationRoute?.let { screenRoute ->
                                    popUpTo(screenRoute) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    )
}
