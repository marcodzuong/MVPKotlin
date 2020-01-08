package com.bach.dv.basemvp.util

import android.content.Context
import android.content.SharedPreferences
import com.bach.dv.basemvp.App

open abstract class SharedPrefsUtils {
    var preferences: SharedPreferences? = null
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var context: Context? = null

    abstract val getKeyShare: String
    fun setContext(context: Context) {
        this.context = context
        sharedPreferences = this.context?.getSharedPreferences(getKeyShare, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    protected fun getInstance(): SharedPreferences? {
        if (preferences == null) {
            preferences =
                App.getAppContext()?.getSharedPreferences(getKeyShare, Context.MODE_PRIVATE)
        }
        return preferences
    }

    fun getStringPreference(key: String): String? {
        preferences = getInstance()
        val value: String? = preferences?.getString(key, null)
        return value
    }
}