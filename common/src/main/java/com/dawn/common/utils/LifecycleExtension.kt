package com.dawn.common.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> LifecycleOwner.safeCollectFlow(flow: Flow<T>, crossinline result: (T) -> Unit) {
    this.lifecycleScope.launch {
        flow.flowWithLifecycle(this@safeCollectFlow.lifecycle, Lifecycle.State.STARTED)
            .collect {
                result(it)
            }
    }
}
