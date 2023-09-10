package com.example.wistron_interview

import android.content.Context

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    private val CHECK_ITEM_KEY = "checkItemKey"

    fun saveCheckItem(checkItem: Int) {
        sharedPreferences.edit().putInt(CHECK_ITEM_KEY, checkItem).apply()
    }

    fun getCheckItem(): Int {
        return sharedPreferences.getInt(CHECK_ITEM_KEY, 0)
    }
}