package com.example.wistron_interview.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.wistron_interview.TaipeiTravelApplication

object Util {
    fun isInternetConnected(): Boolean {
        val cm = TaipeiTravelApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun getString(resourceId: Int): String {
        return TaipeiTravelApplication.instance.getString(resourceId)
    }
}