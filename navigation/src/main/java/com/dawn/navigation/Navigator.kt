package com.dawn.navigation

import android.content.Context
import android.content.Intent

object Navigator {
    fun navigateToMainActivity(context: Context) {
        val intent = Intent(context, Class.forName("path-to-class"))
        context.startActivity(intent)
    }
}