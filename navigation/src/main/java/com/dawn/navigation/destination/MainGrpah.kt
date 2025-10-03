package com.dawn.navigation.destination

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

sealed class BottomNavigationScreen{
    @Serializable
    data object FirstScreen : BottomNavigationScreen()
    @Serializable
    data object SecondScreen : BottomNavigationScreen()
    @Serializable
    data object ThirdScreen : BottomNavigationScreen()
    @Serializable
    data object FourthScreen : BottomNavigationScreen()
}

data class BottomNavigationItem(
//    @DrawableRes val iconRes: Int,
    val index: Int,
    val screen: BottomNavigationScreen,
)
