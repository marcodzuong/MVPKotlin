package com.bach.dv.basemvp.util

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.bach.dv.basemvp.App

abstract class SharedPrefsUtils {

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    abstract val getKeyShare: String
    private fun getInstance(): SharedPreferences? {
        if (preferences == null) {
            preferences =
                App.getAppContext()?.getSharedPreferences(getKeyShare, Context.MODE_PRIVATE)
        }
        return preferences
    }

    var preferences: SharedPreferences? = null


    fun getStringPreference(key: String): String? {
        preferences = getInstance()
        val value: String? = preferences?.getString(key, null)
        return value
    }

    fun setStringPreference(key: String?, value: String): Boolean? {
        val preferences = getInstance()
        when {
            preferences != null || !TextUtils.isEmpty(value) -> {
                val editor = preferences?.edit()
                editor?.putString(key, value)
                return editor?.commit()
            }
        }
        return false
    }


}