package com.dawn.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.dawn.common.presentation.EventManager
import com.dawn.common.presentation.MaxSizeBox
import com.dawn.common.presentation.component.LocalBottomNavigationVisibility
import com.dawn.common.presentation.rememberState
import com.dawn.common.utils.safeCollectFlow
import com.dawn.navigation.destination.BarScreen
import com.dawn.navigation.destination.BottomNavigationItem
import com.dawn.navigation.destination.BottomNavigationScreen
import com.dawn.navigation.destination.FooScreen
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalComposeUiApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        safeCollectFlow(EventManager.events) {
            //TODO: Handle the events bus
        }

        setContent {
            val navController = rememberNavController()
            val layoutDirection = LocalLayoutDirection.current

            // A surface container using the 'background' color from the theme
            CompositionLocalProvider(
                LocalBottomNavigationVisibility provides rememberState { true },
            ) {
                Scaffold(
                    bottomBar = {
                        if (LocalBottomNavigationVisibility.current.value) {
                            BottomNavigationBar(
                                navController = navController,
                            )
                        }
                    },
                    modifier = Modifier
                        .navigationBarsPadding()
                        .imePadding()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavigationScreen.FirstScreen,
                        modifier = Modifier
                            .semantics { testTagsAsResourceId = true }
                            .padding(
                                start = it.calculateStartPadding(layoutDirection),
                                end = it.calculateEndPadding(layoutDirection),
                            ),
                        enterTransition = {
                            fadeIn(animationSpec = tween(0))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(0))
                        },
                        popExitTransition = {
                            fadeOut(animationSpec = tween(0))
                        },
                        popEnterTransition = {
                            fadeIn(animationSpec = tween(0))
                        }
                    ) {
                        composable<BottomNavigationScreen.FirstScreen> { _ ->
                            MaxSizeBox {
                                Text(
                                    text = "This is First Screen"
                                )
                            }
                        }

                        composable<BottomNavigationScreen.SecondScreen> {
                            MaxSizeBox {
                                Text(
                                    text = "This is Second Screen"
                                )
                            }
                        }

                        composable<BottomNavigationScreen.ThirdScreen> {
                            MaxSizeBox {
                                Text(
                                    text = "This is Third Screen"
                                )
                            }
                        }

                        addFourthGraph(
                            bottomPadding = it.calculateEndPadding(layoutDirection),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.addFourthGraph(
        bottomPadding: Dp,
        navController: NavController,
    ) {
        navigation<BottomNavigationScreen.FourthScreen>(
            startDestination = FooScreen,
        ) {
            composable<FooScreen> {
                val widgetBackStackEntry = remember(it) {
                    try {
                        navController.getBackStackEntry(BottomNavigationScreen.FourthScreen)
                    } catch (_: Exception) {
                        null
                    }
                }
                widgetBackStackEntry?.let {
                    LocalBottomNavigationVisibility.current.value = true
                    MaxSizeBox {
                        Text(
                            text = "This is Foo Screen"
                        )
                        Button(
                            onClick = {
                                navController.navigate(BarScreen)
                            }
                        ) {
                            Text(
                                text = "Go to Bar Screen"
                            )
                        }
                    }
                }
            }

            composable<BarScreen> {
                val widgetBackStackEntry = remember(it) {
                    try {
                        navController.getBackStackEntry(BottomNavigationScreen.FourthScreen)
                    } catch (_: Exception) {
                        null
                    }
                }
                widgetBackStackEntry?.let {
                    LocalBottomNavigationVisibility.current.value = false
                    MaxSizeBox {
                        Text(
                            text = "This is Bar Screen"
                        )
                        Button(
                            onClick = navController::navigateUp
                        ) {
                            Text(
                                text = "Back to Foo Screen"
                            )
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun BottomNavigationBar(
        navController: NavController,
    ) {
        val bottomNavigationItems = remember {
            listOf(
                BottomNavigationItem(
                    index = 0,
                    screen = BottomNavigationScreen.FirstScreen
                ),
                BottomNavigationItem(
                    index = 1,
                    screen = BottomNavigationScreen.SecondScreen
                ),
                BottomNavigationItem(
                    index = 2,
                    screen = BottomNavigationScreen.ThirdScreen
                ),
                BottomNavigationItem(
                    index = 3,
                    screen = BottomNavigationScreen.FourthScreen
                ),
            )
        }

        CompositionLocalProvider(
            LocalRippleConfiguration provides null
        ) {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Transparent,
            ) {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value?.destination

                bottomNavigationItems.forEach { item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.hasRoute(item.screen::class) } == true
                    NavigationBarItem(
                        selected = isSelected,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            navController.navigate(item.screen) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
//                        colors = NavigationBarItemDefaults.colors(
//                            selectedIconColor = colorResource(id = CommonR.color.colorSystem_primary_50),
//                            unselectedIconColor = colorResource(id = R.color.bottomNav_defaultIcon),
//                            selectedTextColor = colorResource(id = CommonR.color.colorSystem_primary_50),
//                            unselectedTextColor = colorResource(id = R.color.bottomNav_defaultIcon),
//                            indicatorColor = Color.White
//                        ),
                        icon = {
                            Icon(
                                imageVector = when (item.index) {
                                    0 -> Icons.Filled.Home
                                    1 -> Icons.Filled.Favorite
                                    2 -> Icons.Filled.Person
                                    else -> Icons.Filled.Settings
                                },
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.semantics {
                            contentDescription = item.screen.toString()
                        },
                    )
                }
            }
        }
    }
}