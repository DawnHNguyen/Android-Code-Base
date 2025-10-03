package com.dawn.common.presentation

import com.dawn.domain.entity.AnalyticsEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object EventManager {
    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(message: String) {
        _events.send(message)
    }

    private val _analyticsEvent = Channel<AnalyticsEvent>()
    val analyticsEvent = _analyticsEvent.receiveAsFlow()

    suspend fun sendAnalyticsEvent(analyticsEvent: AnalyticsEvent) {
        _analyticsEvent.send(analyticsEvent)
    }

}
