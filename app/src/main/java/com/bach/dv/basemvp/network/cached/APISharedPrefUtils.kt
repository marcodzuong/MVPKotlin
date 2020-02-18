package com.bach.dv.basemvp.network.cached

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.bach.dv.basemvp.App

class APISharedPrefUtils  {
    private constructor()

    companion object {
        private const val KEY_SHARED_PREF = "KEY_SHARED_PREF"
        var preferences: SharedPreferences? = null
        fun getInstances(): SharedPreferences? {
            if (preferences == null) {
                preferences = App.getAppContext()
                    ?.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE)
            }
            return preferences
        }

        fun setStringPreference(key: String, value: String): Boolean {
            val sharedPreferences = getInstances()
            if (sharedPreferences != null && TextUtils.isEmpty(key)) {
                val editor = sharedPreferences.edit()
                editor.putString(key, value)
                return editor.commit()
            }
            return false
        }

        fun getStringPreference(key: String): String? {
            val sharedPreferences = getInstances()
            if (sharedPreferences != null) {
                return sharedPreferences.getString(key, null)
            }
            return null
        }

        fun removeKey(key: String): Boolean {
            val sharedPreferences = getInstances()
            if (sharedPreferences != null) {
                val editor = sharedPreferences.edit()
                editor.remove(key)
                return editor.commit()
            }
            return false
        }
    }

}