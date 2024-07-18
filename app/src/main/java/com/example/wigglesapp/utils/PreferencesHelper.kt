package com.example.wigglesapp.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper
{
    private const val PREF_NAME = "wiggles_prefs"
    private const val KEY_GREETING_SHOWN = "greeting_shown"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isGreetingShown(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_GREETING_SHOWN, false)
    }
    fun setGreetingShown(context: Context, shown: Boolean){
        getPreferences(context).edit().putBoolean(KEY_GREETING_SHOWN, shown).apply()
    }

    fun resetGreetingShown(context: Context) {
        getPreferences(context).edit().remove(KEY_GREETING_SHOWN).apply()
    }
}