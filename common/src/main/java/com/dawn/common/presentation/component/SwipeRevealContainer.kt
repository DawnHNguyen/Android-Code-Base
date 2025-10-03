package com.dawn.common.presentation.component

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeRevealContainer(
    state: AnchoredDraggableState<Int>,
    revealedWidth: Float,
    revealContent: @Composable BoxScope.() -> Unit,
    mainContent: @Composable BoxScope.() -> Unit,
) {
    Box {
        Box(
            content = revealContent,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset {
                    IntOffset(
                        x = ((-state.requireOffset()) + revealedWidth).roundToInt(),
                        y = 0
                    )
                }
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(
                        x = -state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state = state, orientation = Orientation.Horizontal, reverseDirection = true),
            content = mainContent
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberSwipeRevealState(
    revealedWidth: Float,
): AnchoredDraggableState<Int> {
    val density = LocalDensity.current
    return remember(revealedWidth) {
        AnchoredDraggableState(
            initialValue = 0,
            anchors = DraggableAnchors {
                0 at 0f
                1 at revealedWidth
            },
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 50.dp.toPx() } },
            snapAnimationSpec = tween(),
            decayAnimationSpec = exponentialDecay(),
        )
    }
}