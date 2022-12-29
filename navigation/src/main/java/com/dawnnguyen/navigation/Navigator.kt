package com.dawnnguyen.navigation

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

fun navigateToSomeWhere(context: Context){
    val intent = Intent(context, Class.forName("Your Destination Class Name"))
    ContextCompat.startActivity(context, intent, null)
}