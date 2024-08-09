package com.example.jetpackcomposebase

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposebase.app_drawer.DrawerHeader
import com.example.jetpackcomposebase.app_drawer.NavigationItems
import com.example.jetpackcomposebase.base.LocaleManager
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.navigation.AppNavHost
import com.example.jetpackcomposebase.navigation.Destination
import com.example.jetpackcomposebase.navigation.NAV_LOGIN
import com.example.jetpackcomposebase.ui.custom_compose.AppBar
import com.example.jetpackcomposebase.ui.custom_compose.AppBottomNavigation
import com.example.jetpackcomposebase.ui.theme.MainComposeTheme
import com.example.jetpackcomposebase.utils.DebugLog
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun JetpackBaseApp(
    localeManager: LocaleManager,
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val topBarState = remember { mutableStateOf(ToolBarData()) }
    val bottomBarState = rememberSaveable { mutableStateOf(true) }
    val circularProgress = rememberSaveable { mutableStateOf(false) }

    /**
     * Modal Drawer navigation & NavigationItem
     */

    MainComposeTheme {
        val items = listOf(
            NavigationItems(
                title = stringResource(id = R.string.home_title),
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = Destination.HomeScreen.fullRoute
            ),
            NavigationItems(
                title = stringResource(id = R.string.lbl_setting),
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                badgeCount = 45,
                route = Destination.SettingsScreen.fullRoute
            ),
            NavigationItems(
                title = stringResource(id = R.string.lbl_profile),
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                route = Destination.ProfileScreen.fullRoute
            ),
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        DrawerHeader()
                        Spacer(modifier = Modifier.height(16.dp))
                        items.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                label = { Text(text = item.title) },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                },
                                badge = {
                                    item.badgeCount?.let {
                                        Text(text = it.toString())
                                    }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                },
                drawerState = drawerState
            ) {
                Scaffold(
                    topBar = {
                        if (topBarState.value.isVisible) {
                            AppBar(
                                drawerState = drawerState,
                                topBar = topBarState.value,
                                onDrawerIconClick = { scope.launch { drawerState.open() } },
                                onBackIconClick = {
                                    navController.navigateUp()
                                },
                                onLogoutClick = {
                                    navController.navigate(NAV_LOGIN)
                                },
                                navController = navController
                            )
                        }

                    },
                    bottomBar = {
                        if (bottomBarState.value) {
                            AppBottomNavigation(
                                navController,
                                bottomBarState
                            )
                        }
                    }
                ) { paddingValues ->
                    AppNavHost(
                        navController = navController,
                        startDestination = Destination.SplashScreen,
                        modifier = Modifier.padding(paddingValues),
                        topBar = { topBarState.value = it },
                        bottomBarVisibility = { bottomBarState.value = it },
                        circularProgress = {
                            circularProgress.value = it
                            DebugLog.e("circularProgress:$it")
                        },
                        localeManager = localeManager
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun MainActivityPreview() {
    //JetpackBaseApp(localeManager = LocaleManager)
}
