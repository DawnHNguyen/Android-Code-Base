package com.dawn.common.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
inline fun <T> rememberState(crossinline producer: @DisallowComposableCalls () -> T) =
    remember { mutableStateOf(producer()) }

@Composable
inline fun <T> rememberState(vararg key: Any?, crossinline producer: @DisallowComposableCalls () -> T) =
    remember(key) { mutableStateOf(producer()) }

@Composable
inline fun rememberFloatState(crossinline producer: @DisallowComposableCalls () -> Float) =
    remember { mutableFloatStateOf(producer()) }

@Composable
inline fun rememberFloatState(vararg key: Any?, crossinline producer: @DisallowComposableCalls () -> Float) =
    remember(key) { mutableFloatStateOf(producer()) }

@Composable
inline fun rememberDoubleState(crossinline producer: @DisallowComposableCalls () -> Double) =
    remember { mutableDoubleStateOf(producer()) }

@Composable
inline fun rememberDoubleState(vararg key: Any?, crossinline producer: @DisallowComposableCalls () -> Double) =
    remember(key) { mutableDoubleStateOf(producer()) }

@Composable
inline fun rememberIntState(crossinline producer: @DisallowComposableCalls () -> Int) =
    remember { mutableIntStateOf(producer()) }

@Composable
inline fun rememberIntState(vararg key: Any?, crossinline producer: @DisallowComposableCalls () -> Int) =
    remember(key) { mutableIntStateOf(producer()) }

@Composable
inline fun rememberLongState(crossinline producer: @DisallowComposableCalls () -> Long) =
    remember { mutableLongStateOf(producer()) }

@Composable
inline fun rememberLongState(vararg key: Any?, crossinline producer: @DisallowComposableCalls () -> Long) =
    remember(key) { mutableLongStateOf(producer()) }

@Composable
inline fun <T> rememberDerivedState(crossinline calculation: @DisallowComposableCalls () -> T) =
    remember { derivedStateOf { calculation() } }

@Composable
inline fun rememberDerivedState(vararg key: Any?, crossinline calculation: @DisallowComposableCalls () -> Any?) =
    remember(key) { derivedStateOf { calculation() } }


@Composable
fun MaxSizeBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Composable
fun MaxSizeColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        content = content
    )
}

@Composable
fun MaxSizeRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}

@Composable
fun MaxWidthBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Composable
fun MaxWidthColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        content = content
    )
}

@Composable
fun MaxWidthRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}