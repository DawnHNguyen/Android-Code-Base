package com.dawn.common.presentation.annalytic

import com.dawn.common.utils.toBundle
import com.dawn.domain.entity.AnalyticsEvent
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object AnalyticsLogger {
    private val firebaseAnalytics = Firebase.analytics

    suspend fun logEvent(analyticsEvent: AnalyticsEvent, shouldNotifyToEventManager: Boolean = true) {
        firebaseAnalytics.logEvent(analyticsEvent.eventName, analyticsEvent.params?.toBundle())
//        if (shouldNotifyToEventManager) EventManager.sendAnalyticsEvent(analyticsEvent)
    }
}