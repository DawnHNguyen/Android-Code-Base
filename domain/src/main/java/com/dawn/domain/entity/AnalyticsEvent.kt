package com.dawn.domain.entity

data class AnalyticsEvent(
    val eventName: String,
    val params: Map<String, Any>? = null,
)