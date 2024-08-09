package com.example.jetpackcomposebase.base

/**
 * Toolbar manages items and visibility according to
 */
data class ToolBarData(
    var title: String = "",
    var isVisible: Boolean = false,
    var isBackIconVisible: Boolean = false,
    var isDrawerIcon: Boolean = false,
    var isCenterLogoVisible: Boolean = false,
    var isTitleVisible: Boolean = false,
    var isSettingsIconVisible : Boolean = false,
    var isDeleteIconVisible : Boolean = false
)
