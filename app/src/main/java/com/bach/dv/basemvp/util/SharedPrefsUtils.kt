package com.bach.dv.basemvp.util

import android.content.Context
import android.content.SharedPreferences
import com.bach.dv.basemvp.App

open class SharedPrefsUtils {

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    companion object {
        const val KEY_SHARED_PREF = ""
        var preferences: SharedPreferences? = null
        protected fun getInstance(): SharedPreferences? {
            if (preferences == null) {
                preferences =
                    App.getAppContext()?.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE)
            }
            return preferences
        }

        fun getStringPreference(key: String): String? {
            preferences = getInstance()
            val value: String? = preferences?.getString(key, null)
            return value
        }
    }
}