package com.example.jetpackcomposebase.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.jetpackcomposebase.R

/**
 * Bottom Nav Items
 */
sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val navRoute: Destination
) {
    data object Home : BottomNavItem(R.string.home_title, R.drawable.ic_home, Destination.HomeScreen)
    data object Setting :
        BottomNavItem(R.string.lbl_setting, R.drawable.ic_settings, Destination.SettingsScreen)

    data object Profile :
        BottomNavItem(R.string.lbl_profile, R.drawable.ic_profile, Destination.ProfileScreen)

}
