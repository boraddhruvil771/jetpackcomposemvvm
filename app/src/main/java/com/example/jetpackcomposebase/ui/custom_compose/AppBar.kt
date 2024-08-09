package com.example.jetpackcomposebase.ui.custom_compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import kotlinx.coroutines.CoroutineScope

/**
 * This is Common App Bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    drawerState: DrawerState,
    topBar: ToolBarData = ToolBarData(),
    onDrawerIconClick: () -> Unit,
    onBackIconClick: () -> Unit,
    onLogoutClick: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(text = topBar.title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (topBar.isDrawerIcon) {
                IconButton(onClick = onDrawerIconClick) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Toggle drawer"
                    )
                }
            }

            if (topBar.isBackIconVisible) {
                IconButton(onClick = onBackIconClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back arrow"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onLogoutClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "Logout"
                )
            }
        }
    )
}
