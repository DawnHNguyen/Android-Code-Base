package com.dawn.common.presentation.component

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalBottomNavigationVisibility = compositionLocalOf { mutableStateOf(true) }
