package com.dawn.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Parcelable
import androidx.core.content.IntentCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun Activity.checkForInternet(): Boolean = withContext(Dispatchers.IO) {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return@withContext false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return@withContext false

    return@withContext when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

inline fun <reified T : Parcelable> Intent.getParcel(key: String): T? =
    IntentCompat.getParcelableExtra(this, key, T::class.java)