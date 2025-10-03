package com.dawn.data.local

import app.cash.sqldelight.ColumnAdapter

internal class EnumColumnAdapter<T : Enum<T>>(
    private val valueOf: (String) -> T, // A function to map String to Enum
    private val default: T,             // Default value for invalid strings
) : ColumnAdapter<T, String> {
    override fun decode(databaseValue: String): T {
        return try {
            valueOf(databaseValue)
        } catch (e: Exception) {
            default
        }
    }

    override fun encode(value: T): String {
        return value.name
    }
}