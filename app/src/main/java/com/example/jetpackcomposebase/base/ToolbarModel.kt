package com.example.jetpackcomposebase.base

data class ToolbarModel(
    var isVisible: Boolean = false,
    var title: String? = "",
    var isBottomNavVisible: Boolean = false,
    var isToolbarWithBackVisible: Boolean = false,
    var isBackBtnVisible: Boolean = true
)